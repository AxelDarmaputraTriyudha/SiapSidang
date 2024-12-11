package com.RPL.SiapSidang.Dosen.Home;

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
@RequestMapping("/dosen")
public class DosenHomepageController {
    @Autowired
    JDBCDosenRepositoryImplementation dosenRepository;

    @GetMapping("/home")
    @RequiredRole("Dosen")
    public String index(Model model,
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(required = false, defaultValue = "") LocalDate tgl_awal,
            @RequestParam(required = false, defaultValue = "") LocalDate tgl_akhir,
            @RequestParam(required = false, defaultValue = "") String semester,
            @RequestParam(required = false, defaultValue = "0") int tahun,
            HttpSession session){

        List<DataSidang> dataSidangList = dosenRepository.findAll(filter, tgl_awal, tgl_akhir, semester, tahun, (String) session.getAttribute("nik"));

        model.addAttribute("tgl_awal", tgl_awal);
        model.addAttribute("tgl_akhir", tgl_akhir);
        model.addAttribute("filter", filter);
        model.addAttribute("sidang", dataSidangList);
        model.addAttribute("semester", semester);
        model.addAttribute("tahun", tahun);
        return "/dosen/index";
    }
}
