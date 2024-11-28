package com.RPL.SiapSidang.Koord.BuatJadwal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/koord")
public class KoordController {
    @Autowired
    private KoordRepository repo;
    
    @GetMapping("/buatJadwal1")
    public String buatJadwal(){
        return "koord/BuatJadwal/index1";
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
        List<Komponen> list = this.repo.getAllKomponen("Ganjil", 2023);
        model.addAttribute("nilaiList", list);
        return "koord/BuatJadwal/index3";
    }
}
