package com.RPL.SiapSidang.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/loginRPL")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public String loginPage(HttpSession session){
        return "/loginRPL/index";
    }

}
