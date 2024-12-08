package com.RPL.SiapSidang.Koord.Nilai;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/koord")
public class NilaiController {
    @GetMapping("/tambahNilai")
    public String index(HttpSession session, Model model) {
        model.addAttribute("nilaiKoord", session.getAttribute("nilaiKoord"));
        model.addAttribute("successMessage", session.getAttribute("successMessage"));
        session.removeAttribute("successMessage"); // Agar pesan hanya muncul sekali
        return "koord/Nilai/index";
    }

    @PostMapping("/tambahNilai")
    public String tambahNilai(
        @RequestParam(value = "nilaiKoord", required = true) Integer nilai,
        HttpSession session, Model model) {
        if (nilai != null) {
            session.setAttribute("nilaiKoord", nilai);
            session.setAttribute("successMessage", "Nilai berhasil disimpan!");
        }
        return "redirect:/koord/tambahNilai";
    }

}
