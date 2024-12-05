package com.RPL.SiapSidang.Koord.Nilai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/koord")
public class NilaiController {
    @GetMapping("/tambahNilai")
    public String index(){
        return "koord/Nilai/index";
    }
}
