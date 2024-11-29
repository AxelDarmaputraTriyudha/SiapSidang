package com.RPL.SiapSidang.Koord.BuatJadwal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/koord")
public class KoordController {
    @Autowired
    private KoordRepository repo;
    private String semester;
    private int tahun;
    
    @GetMapping("/buatJadwal1")
    public String buatJadwal1(HttpSession session, Model model) {
        model.addAttribute("namaMahasiswa", session.getAttribute("namaMahasiswa"));
        model.addAttribute("npm", session.getAttribute("npm"));
        model.addAttribute("semester", session.getAttribute("semester"));
        model.addAttribute("tahun", session.getAttribute("tahun"));
        model.addAttribute("tgl", session.getAttribute("tgl"));
        model.addAttribute("waktu", session.getAttribute("waktu"));
        model.addAttribute("tempat", session.getAttribute("tempat"));
        System.out.println(session.getAttribute("namaMahasiswa"));
        return "koord/BuatJadwal/index1";
    }

    @PostMapping("/buatJadwal1")
    public String buatJadwal11(
        @RequestParam("namaMahasiswa") String namaMahasiswa,
        @RequestParam("npm") String npm,
        @RequestParam("semester") String semester,
        @RequestParam("tahun") String tahun,
        @RequestParam("tgl") LocalDate tgl,
        @RequestParam("waktu") String waktu,
        @RequestParam("tempat") String tempat, 
        HttpSession session
        ){
            session.setAttribute("namaMahasiswa", namaMahasiswa);
            session.setAttribute("npm", npm);
            session.setAttribute("semester", semester);
            session.setAttribute("tahun", tahun);
            session.setAttribute("tgl", tgl);
            session.setAttribute("waktu", waktu);
            session.setAttribute("tempat", tempat);
            this.semester = semester;
            this.tahun = Integer.parseInt(tahun);
            System.out.println(semester + " " + tahun);
            System.out.println(session.getAttribute("namaMahasiswa"));
            return "redirect:/koord/buatJadwal2";
        }

    @GetMapping("/buatJadwal2")
    public String buatJadwal2(Model model){
        List<Dosen> list = this.repo.getAllDosen();
        model.addAttribute("pb1", list);
        model.addAttribute("pb2", list);
        model.addAttribute("pu1", list);
        model.addAttribute("pu2", list);
        return "koord/BuatJadwal/index2";
    }

    @GetMapping("/buatJadwal3")
    public String buatJadwal3(Model model){
        List<Komponen> list = this.repo.getAllKomponen(this.semester, this.tahun);
        model.addAttribute("nilaiList", list);
        return "koord/BuatJadwal/index3";
    }
}
