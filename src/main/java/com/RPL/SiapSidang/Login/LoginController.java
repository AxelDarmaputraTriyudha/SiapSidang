package com.RPL.SiapSidang.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public String loginPage(){
        return "login/login";
    }

    @PostMapping("/login")
    public String handleLogin (@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model ){

        String result = loginService.login(email, password, session);

        if (result.contains("berhasil")) {
            return "redirect:/dashboard";  // Login berhasil
        }

        model.addAttribute("status", "failed");  // Login gagal
        return "login";  // Kembali ke halaman login
    }

    // Menampilkan dashboard
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session) {
        if (session.getAttribute("id") == null) {
            return "redirect:/login";  // Jika belum login, redirect ke login
        }
        return "/koord/Home/tabelJadwal";  // Menampilkan halaman dashboard
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Hapus session
        return "redirect:/login";  // Kembali ke halaman login
    }
}
