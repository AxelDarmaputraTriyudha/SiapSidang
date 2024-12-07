package com.RPL.SiapSidang.Koord.BuatKomponen;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/koord")

public class KomponenController {
    @GetMapping("/buatKomponen")
    public String index(){
        return "koord/BuatKomponen/index";
    }    

    @PostMapping("/buatKomponen")
    public String index2(){
        System.out.println("masuk");
        return "koord/BuatKomponen/index"; 
    }
}
