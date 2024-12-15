package com.RPL.SiapSidang.Mahasiswa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RPL.SiapSidang.RequiredRole;
import com.RPL.SiapSidang.Koord.Home.DataSidang;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mahasiswa")
public class MahasiswaController {


    @Autowired
    JDBCMahasiswaRepository mahasiswaRepository;

    @GetMapping("/home")
    @RequiredRole("mahasiswa")
    public String index(Model model,
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(required = false, defaultValue = "") LocalDate tgl_awal,
            @RequestParam(required = false, defaultValue = "") LocalDate tgl_akhir,
            @RequestParam(required = false, defaultValue = "") String semester,
            @RequestParam(required = false, defaultValue = "0") int tahun,
            HttpSession session){
        
        String npm = (String) session.getAttribute("npm"); 
        List<DataSidang> dataSidangList = mahasiswaRepository.findAll(tgl_awal, tgl_akhir, semester, tahun, npm);

        model.addAttribute("tgl_awal", tgl_awal);
        model.addAttribute("tgl_akhir", tgl_akhir);
        model.addAttribute("sidang", dataSidangList);
        model.addAttribute("semester", semester);
        model.addAttribute("tahun", tahun);

        // logging
        System.out.println(session.getAttribute("npm") + " " + session.getAttribute("nama") + " " + session.getAttribute("peran"));
        return "/mahasiswa/index";
    }

    @GetMapping("/DetailJadwal")
    @RequiredRole("mahasiswa")
    public String index(Model model,
            @RequestParam(required = true) String npm,
            HttpSession session){

        System.out.println((String) session.getAttribute("peran"));
        List<DataSidang> data = mahasiswaRepository.findDetail(npm);

        model.addAttribute("data", data.get(3));

        // list nama dosen
        String penguji1 = "", penguji2 = "", pembimbing = "";
        
        // assign nama dosen sesuai peran
        for (int i = 0; i<data.size(); i++){
            DataSidang dataNow = data.get(i);
            switch (dataNow.getPeran()) {
                case "PB1":
                    pembimbing = dataNow.getNama_dosen();
                    break;
                case "PU1":
                    penguji1 = dataNow.getNama_dosen();
                    break;
                case "PU2":
                    penguji2 = dataNow.getNama_dosen();
                    break;
                default:
                    break;
            }
        }

        // add nama-nama dosen
        model.addAttribute("nama_pu1", penguji1);
        model.addAttribute("nama_pu2", penguji2);
        model.addAttribute("nama_pb", pembimbing);
        model.addAttribute("npm", npm);

        return "/mahasiswa/DetailJadwal/detailJadwal";
    }

    @GetMapping("/nilai")
    @RequiredRole("mahasiswa")
    public String nilaiView(){

        return "/mahasiswa/nilai/nilaiMhs";
    }
}
