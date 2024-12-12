package com.RPL.SiapSidang.BAP;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/koord")
public class BAPController {

    @GetMapping("/generateBAP")
    public String index(){
        return "/bap/index";
    }
}
