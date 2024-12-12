package com.RPL.SiapSidang.Pembimbing.CatatanSidang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequestMapping("/pembimbing/catatan")
public class CatatanSidangController {

    @Autowired
    private JDBCCatatan jdbcCatatan;

    // Method untuk menampilkan halaman catatan
    @GetMapping
    public String showCatatanPage(Model model) {
        // Anda dapat menambahkan atribut model jika diperlukan
        return "/pembimbing/CatatanSidang/catatan"; // Mengembalikan nama view catatan.html
    }

    @GetMapping("/data")
    public Map<String, Object> getData(@RequestParam int idTugasAkhir) {
        return jdbcCatatan.findDataByIdTugasAkhir(idTugasAkhir);
    }

    @PostMapping("/save")
    public String saveCatatan(@RequestParam int idTugasAkhir, @RequestBody String catatan) {
        jdbcCatatan.saveCatatan(idTugasAkhir, catatan);
        return "Catatan berhasil disimpan.";
    }
}