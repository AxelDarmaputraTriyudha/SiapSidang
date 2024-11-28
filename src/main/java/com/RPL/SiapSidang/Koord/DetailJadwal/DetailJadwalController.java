package com.RPL.SiapSidang.Koord.DetailJadwal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/koord")
public class DetailJadwalController {
    @GetMapping("/DetailJadwal")
    public String index(){
        return "/koord/DetailJadwal/index";
    }
}
