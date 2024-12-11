package com.RPL.SiapSidang.Koord.BuatKomponen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.RPL.SiapSidang.RequiredRole;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/koord")
public class KomponenController {
    @Autowired
    private JDBCKomponen komponenRepo;
    
        @GetMapping("/buatKomponen")
        @RequiredRole("koordinator")
        public String index(){
            return "koord/BuatKomponen/index";
        }
        
        @PostMapping("/buatKomponen")
        @RequiredRole("koordinator")
        public String index2(
            @RequestParam("semester") String semester,
            @RequestParam("tahun") String tahun,
            @RequestParam("deskripsi") List<String> deskripsiList,
            @RequestParam("penguji") List<String> pengujiList,
            @RequestParam("pembimbing") List<String> pembimbingList,
            Model model, HttpSession session,
            RedirectAttributes redirectAttributes) {
        
            int sumPenguji = 0;
            int sumPembimbing = 0;
            List<Komponen> listKomponen = new ArrayList<>();
        
            for (int i = 0; i < deskripsiList.size(); i++) {
                String deskripsi = deskripsiList.get(i);
                double penguji = Double.parseDouble(pengujiList.get(i));
                double pembimbing = Double.parseDouble(pembimbingList.get(i));
        
                sumPenguji += penguji;
                sumPembimbing += pembimbing;
    
                Komponen komponen = new Komponen(deskripsi, penguji, pembimbing, semester, tahun);
                listKomponen.add(komponen);
            }
    
            if (sumPenguji != 100 || sumPembimbing != 100) {
                model.addAttribute("semester", semester);
                session.setAttribute("semester", semester);
                model.addAttribute("tahun", tahun);
                session.setAttribute("tahun", tahun);

                model.addAttribute("nilaiList", createNilaiList(deskripsiList, pengujiList, pembimbingList));

                if (sumPenguji < 100 && sumPembimbing < 100){
                    model.addAttribute("errorMessage", "Total bobot penguji dan pembimbing kurang dari 100!");
                }
                else if (sumPembimbing < 100){
                    model.addAttribute("errorMessage", "Total bobot pembimbing kurang dari 100!");
                }
                else if (sumPenguji < 100){
                    model.addAttribute("errorMessage", "Total bobot penguji kurang dari 100!");
                }

                if (sumPenguji > 100 && sumPembimbing > 100){
                    model.addAttribute("errorMessage", "Total bobot penguji dan pembimbing lebih dari 100!");
                }
                else if (sumPembimbing > 100){
                    model.addAttribute("errorMessage", "Total bobot pembimbing lebih dari 100!");
                }
                else if (sumPenguji > 100){
                    model.addAttribute("errorMessage", "Total bobot penguji lebih dari 100!");
                }
                return "koord/BuatKomponen/index";
            }       
        
            // Jika validasi berhasil
            for(Komponen komponen : listKomponen){
                komponenRepo.addKomponen(komponen);
            }
            redirectAttributes.addFlashAttribute("message", "Komponen berhasil disimpan!");
            session.invalidate();
            return "redirect:/koord/home";
    }
    

    private List<Map<String, String>> createNilaiList(List<String> deskripsiList, List<String> pengujiList, List<String> pembimbingList) {
        List<Map<String, String>> nilaiList = new ArrayList<>();
        for (int i = 0; i < deskripsiList.size(); i++) {
            Map<String, String> nilai = new HashMap<>();
            nilai.put("deskripsi", deskripsiList.get(i));
            nilai.put("penguji", pengujiList.get(i));
            nilai.put("pembimbing", pembimbingList.get(i));
            nilaiList.add(nilai);
        }
        return nilaiList;
    }

    @ModelAttribute("nilaiList")
    @RequiredRole("koordinator")
    public List<Map<String, String>> getNilaiList(HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Map<String, String>> nilaiList = (List<Map<String, String>>) session.getAttribute("nilaiList");
        return nilaiList != null ? nilaiList : new ArrayList<>();
    }
}
