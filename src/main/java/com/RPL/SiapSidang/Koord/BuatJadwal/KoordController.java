package com.RPL.SiapSidang.Koord.BuatJadwal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/koord")

public class KoordController {
    
    @GetMapping("/buatJadwal1")
    public String buatJadwal(){
        return "koord/BuatJadwal/index1";
    }

    @GetMapping("/buatJadwal2")
    public String buatJadwal2(){
        return "koord/BuatJadwal/index2";
    }
}
