package com.RPL.SiapSidang.Penguji.NilaiMahasiswa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/penguji")
public class NilaiMahasiswaController {
    @Autowired
    private PengujiNilaiRepository pengujiNilaiRepository;

    // Menampilkan halaman nilai mahasiswa
    @GetMapping("/nilaiMahasiswa")
    public String showPage(Model model, HttpSession session) {
        // hardcode session
        session.setAttribute("npm", "6182201101");
        session.setAttribute("nama", "Ayasha Kayla");
        session.setAttribute("judul", "Pengaruh pengerjaan tugas besar RPL terhadap mental mahasiswa informatika");
        
        // Buat objek Mahasiswa dari session
        Mahasiswa mahasiswa = new Mahasiswa(
            (String) session.getAttribute("npm"),
            (String) session.getAttribute("nama"),
            (String) session.getAttribute("judul")
        );
        model.addAttribute("mahasiswa", mahasiswa);

        // Daftar komponen nilai
        List<KomponenNilai> komponenNilai = pengujiNilaiRepository.getKomponen();
        model.addAttribute("komponenNilai", komponenNilai);

        return "penguji/nilaiMahasiswa"; 
    }

    // @PostMapping("/savenilai")
    // public String saveNilai(
    //         @RequestParam("deskripsi") List<String> deskripsiList,
    //         @RequestParam("nilai") List<String> nilaiList) {
    
    //     // Debugging: Tampilkan data yang diterima
    //     for (int i = 0; i < deskripsiList.size(); i++) {
    //         System.out.println(deskripsiList.get(i) + ", Nilai: " + nilaiList.get(i));
    //     }
    
    //     // Tambahkan logika untuk menyimpan data di database jika diperlukan
        
    //     return "redirect:/penguji/nilaiMahasiswa";
    // }      

    @PostMapping("/savenilai")
    public String saveNilai(
            @RequestParam("deskripsi") List<String> deskripsiList,
            @RequestParam("nilai") List<Double> nilaiList,
            HttpSession session, Model model) {

           String npm = (String) session.getAttribute("npm");

        // Loop melalui setiap deskripsi dan nilai
        for (int i = 0; i < deskripsiList.size(); i++) {
            String deskripsi = deskripsiList.get(i);
            double nilai = nilaiList.get(i);
            // Ambil bobot berdasarkan deskripsi
            List<Bobot> bobotList = pengujiNilaiRepository.getBobot(deskripsi);
    
            // Pastikan bobot ditemukan
            if (bobotList.isEmpty()) {
                throw new IllegalArgumentException("Bobot tidak ditemukan untuk deskripsi: " + deskripsi);
            }
    
            // Hitung total bobot
            int totalBobot = bobotList.stream().mapToInt(Bobot::getBobot).sum();
    
            // Simpan nilai penguji
            pengujiNilaiRepository.saveNilaiPenguji(nilai, totalBobot, npm);
        }
    
        return "redirect:/penguji/nilaiMahasiswa";
    }
    


}
