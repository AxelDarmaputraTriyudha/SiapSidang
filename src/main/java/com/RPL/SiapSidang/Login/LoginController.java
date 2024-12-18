package com.RPL.SiapSidang.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller

public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String loginPage(){
        return "login/loginPage";
    }

    @PostMapping("/login")
    public String handleLogin (@RequestParam String email, @RequestParam String password, HttpSession session, Model model ){

        User user = loginService.login(email, password);
        
        if(user!=null){
            if(user.getNomor().contains("618") ){
                session.setAttribute("npm", user.getNomor());
                session.setAttribute("nama", user.getNama());
                session.setAttribute("peran", "mahasiswa");
                return "redirect:/mahasiswa/home";
            }

            else if( user.getNomor().contains("2001")){

                if(user.getIsKoord()){
                    session.setAttribute("nik", user.getNomor());
                    session.setAttribute("nama", user.getNama());
                    session.setAttribute("kode_nama", user.getKode_nama());
                    session.setAttribute("peran", "koordinator");
                    return "redirect:/koord/home";
                }
                else{
                    session.setAttribute("nik", user.getNomor());
                    session.setAttribute("nama", user.getNama());
                    session.setAttribute("kode_nama", user.getKode_nama());
                    session.setAttribute("peran", "Dosen");
                    return "redirect:/dosen/home";
                }
            }
        }

        model.addAttribute("status", "failed");  // Login gagal
        return "/login/loginPage";
    }
    

    @GetMapping("/loginRequired")
    public String errorLoginRequired (){
        return "/ErrorLogin/errorLoginPage";
    }

    @GetMapping("/wrongRole")
    public String errorWrongRole () {
        return "/ErrorLogin/errorWrong";
    }

    @GetMapping("/directHome")
    public String goHome (HttpSession session){
        String peran = (String) session.getAttribute("peran");
        if(peran.contains("koordinator")){
            return "redirect:/koord/home";
        }
        else if(peran.contains("Dosen")){
            return "redirect:/dosen/home";
        }
        else{
            return "redirect:/mahasiswa/home";
        }
    }


    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Hapus session
        return "redirect:/login";  // Kembali ke halaman login
    }
}
