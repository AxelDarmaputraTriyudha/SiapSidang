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

import com.RPL.SiapSidang.Koord.BuatJadwal.Dosen;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/penguji")
public class NilaiMahasiswaController {
    @Autowired
    private PengujiNilaiRepository pengujiNilaiRepository;

    @Autowired
    private JDBDNilai jdbdNilai; 
    // Menampilkan halaman nilai mahasiswa
    @GetMapping("/nilaiMahasiswa")
    public String showPage(Model model, HttpSession session, 
                           @RequestParam(value = "npm", required = false) String npm, 
                           @RequestParam(value = "peran", required = false) String peran) {
     
          
        if (peran == null) {
            throw new IllegalArgumentException("Peran cannot be null");
        }
        // mengambil informasi mahasiswa berdasarakn npm 
        if (npm != null) {
            Mahasiswa mahasiswa = jdbdNilai.getNPM(npm); 
            if (mahasiswa != null) {
                model.addAttribute("nama", mahasiswa.getNama());
                model.addAttribute("judul", mahasiswa.getJudul());
                System.out.println("Nama: " + mahasiswa.getNama() + ", Judul: " + mahasiswa.getJudul()); // Debugging

            }
            session.setAttribute("npm", npm);
            model.addAttribute("npm", npm);
        }

        if (peran != null) {
            session.setAttribute("peran", peran);
            model.addAttribute("peran", peran);
            System.out.println(peran);
        }


        // Daftar komponen nilai
        List<KomponenNilai> komponenNilai = pengujiNilaiRepository.getKomponen(npm,peran);
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

    // @PostMapping("/savenilai")
    // public String saveNilai(
    //         @RequestParam("deskripsi") List<String> deskripsiList,
    //         @RequestParam("nilai") List<Double> nilaiList,
    //         HttpSession session, Model model) {

    //        String npm = (String) session.getAttribute("npm");

    //     // Loop melalui setiap deskripsi dan nilai
    //     for (int i = 0; i < deskripsiList.size(); i++) {
    //         String deskripsi = deskripsiList.get(i);
    //         double nilai = nilaiList.get(i);
    //         // Ambil bobot berdasarkan deskripsi
    //         List<Bobot> bobotList = pengujiNilaiRepository.getBobot(deskripsi);
    
    //         // Pastikan bobot ditemukan
    //         if (bobotList.isEmpty()) {
    //             throw new IllegalArgumentException("Bobot tidak ditemukan untuk deskripsi: " + deskripsi);
    //         }
    
    //         // Hitung total bobot
    //         int totalBobot = bobotList.stream().mapToInt(Bobot::getBobot).sum();
    
    //         // Simpan nilai penguji
    //         pengujiNilaiRepository.saveNilaiPenguji(nilai, totalBobot, npm);
    //     }
    
    //     return "redirect:/penguji/nilaiMahasiswa";
    // }


    // Method untuk menyimpan nilai mahasiswa
    @PostMapping("/savenilai")
    public String saveNilai(@RequestParam("deskripsi") List<String> deskripsi,
                            @RequestParam("nilai") List<Double> nilai,
                            @RequestParam(value = "npm", required = false) String npm,
                            @RequestParam(value = "peran", required = false) String peran,
                            Model model,HttpSession session ) {

        // ambil 'npm' from session
        if (npm != null) {
            session.setAttribute("npm", npm);
            model.addAttribute("npm", npm);
        } else {
            model.addAttribute("message", "NPM tidak ditemukan.");
            return "errorPage"; // Handle case where NPM is not available
        }

         // ambil 'peran' from session
         if (peran != null) {
            session.setAttribute("peran", peran);
            model.addAttribute("peran", peran);
            System.out.println(peran);
        }else{
            System.out.println("peran tidak ditemukan");
        }
        
        if (deskripsi.size() != nilai.size()) {
            throw new IllegalArgumentException("Jumlah deskripsi dan nilai tidak cocok");
        }

        double totalNilaiAkhir = 0.0;

        for (int i = 0; i < deskripsi.size(); i++) {
            String komponenDeskripsi = deskripsi.get(i);
            double nilaiInput = nilai.get(i);

            // Ambil bobot berdasarkan deskripsi
            List<Bobot> bobotList = jdbdNilai.getBobot(komponenDeskripsi);
            if (!bobotList.isEmpty()) {
                int bobot = bobotList.get(0).getBobot();

                // Hitung nilai akhir untuk komponen ini
                double nilaiAkhir = (nilaiInput * bobot) / 100.0;

                // Tambahkan ke total nilai akhir
                totalNilaiAkhir += nilaiAkhir;
            }
        }

        // Simpan nilai total ke database
        jdbdNilai.saveNilaiPenguji(totalNilaiAkhir, npm);

        // Redirect atau tampilkan halaman sukses
        model.addAttribute("message", "Nilai berhasil disimpan.");
        return "redirect:/penguji/nilaiMahasiswa?npm=" + npm + "&peran=" + peran;

    }
}
    



