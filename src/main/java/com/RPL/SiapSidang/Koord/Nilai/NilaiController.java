package com.RPL.SiapSidang.Koord.Nilai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RPL.SiapSidang.RequiredRole;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/koord")
public class NilaiController {
    @Autowired
    private JDBCNilaiKoord jdbcNilai;

    @GetMapping("/tambahNilai/{npm}")
    @RequiredRole("koordinator")
    public String index(HttpSession session, Model model, @PathVariable String npm) {
        // Tambahkan nilai dari database ke model
        NilaiKoord nilai = jdbcNilai.getNilai(npm);
        if (nilai != null) {
            model.addAttribute("nilaiKoord", nilai.getNilai_koord());
        }

        // Tambahkan successMessage ke model jika ada
        String successMessage = (String) session.getAttribute("successMessage");
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage"); // Hapus setelah diambil
        }

        return "koord/Nilai/index";
    }

    @PostMapping("/tambahNilai/{npm}")
    @RequiredRole("koordinator")
    public String tambahNilai(
        @RequestParam(value = "nilaiKoord", required = true) Double nilai,
        HttpSession session, @PathVariable String npm) {
            
        jdbcNilai.tambahNilai(npm, nilai);
        
        session.setAttribute("nilaiKoord", nilai);
        session.setAttribute("successMessage", "Nilai berhasil disimpan!");
        return "redirect:/koord/tambahNilai/" + npm;
    }

}
