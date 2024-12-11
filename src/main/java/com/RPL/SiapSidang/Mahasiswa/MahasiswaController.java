package com.RPL.SiapSidang.Mahasiswa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mahasiswa")
public class MahasiswaController {
    @GetMapping("/home")
    public String index(){
        return "/mahasiswa/index";
    }
}
