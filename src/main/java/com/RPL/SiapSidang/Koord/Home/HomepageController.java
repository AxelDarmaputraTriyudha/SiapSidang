package com.RPL.SiapSidang.Koord.Home;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RPL.SiapSidang.RequiredRole;

@Controller
@RequestMapping("/koord")
public class HomepageController {
    @Autowired
    JDBCDataSidangImplementation dataSidangRepo;

    @GetMapping("/home")
    @RequiredRole("koordinator")
    public String index(Model model,
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(required = false, defaultValue = "") LocalDate tgl_awal,
            @RequestParam(required = false, defaultValue = "") LocalDate tgl_akhir,
            @RequestParam(required = false, defaultValue = "") String semester,
            @RequestParam(required = false, defaultValue = "0") int tahun){

        List<DataSidang> dataSidangList = dataSidangRepo.findAll(filter, tgl_awal, tgl_akhir, semester, tahun);

        model.addAttribute("tgl_awal", tgl_awal);
        model.addAttribute("tgl_akhir", tgl_akhir);
        model.addAttribute("filter", filter);
        model.addAttribute("sidang", dataSidangList);
        model.addAttribute("semester", semester);
        model.addAttribute("tahun", tahun);
        return "/koord/home/tabelJadwal";
    }
}
