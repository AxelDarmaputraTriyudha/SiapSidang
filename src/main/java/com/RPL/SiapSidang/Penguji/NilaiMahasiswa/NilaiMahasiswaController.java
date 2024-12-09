package com.RPL.SiapSidang.Penguji.NilaiMahasiswa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/penguji")
public class NilaiMahasiswaController {

    @Autowired
    private PengujiNilaiService service;

    // Menampilkan halaman nilai mahasiswa
    @GetMapping("/nilaiMahasiswa")
    public String showPage() {
        return "penguji/nilaiMahasiswa"; // Mengarah ke template browse.html
    }

    // Menyimpan data nilai mahasiswa
    @PostMapping("/nilai/save")
    public String saveNilai(
        @RequestParam String nama,
        @RequestParam String npm,
        @RequestParam String topikTugasAkhir,
        @RequestParam int tataTulisLaporan,
        @RequestParam int kelengkapanMateri,
        @RequestParam int pencapaianTujuan,
        @RequestParam int penguasaanMateri,
        @RequestParam int presentasi
    ) {
        PengujiNilai nilai = new PengujiNilai();
        nilai.setNama(nama);
        nilai.setNpm(npm);
        nilai.setTopikTugasAkhir(topikTugasAkhir);
        nilai.setTataTulisLaporan(tataTulisLaporan);
        nilai.setKelengkapanMateri(kelengkapanMateri);
        nilai.setPencapaianTujuan(pencapaianTujuan);
        nilai.setPenguasaanMateri(penguasaanMateri);
        nilai.setPresentasi(presentasi);

        service.saveNilai(nilai);
        return "redirect:/penguji/nilai";
    }
}
