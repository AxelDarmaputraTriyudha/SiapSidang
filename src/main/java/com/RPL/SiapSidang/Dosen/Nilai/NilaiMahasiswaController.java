package com.RPL.SiapSidang.Dosen.Nilai;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dosen")
public class NilaiMahasiswaController {
    @Autowired
    private NilaiRepository pengujiNilaiRepository;

    @Autowired
    private JDBDNilai jdbdNilai; 
    // tampilkan halaman nilai mahasiswa
    @GetMapping("/nilaiMahasiswa")
    public String showPage(Model model, HttpSession session, 
                           @RequestParam(value = "npm", required = false) String npm, 
                           @RequestParam(value = "peran", required = false) String peran) {
     
        model.addAttribute("peran", peran);
        if (peran == null) {
            throw new IllegalArgumentException("Peran cannot be null");
        }
        
        // mengambil informasi mahasiswa berdasarakn npm 
        if (npm != null) {
            Mahasiswa mahasiswa = jdbdNilai.getNPM(npm); 
            if (mahasiswa != null) {
                model.addAttribute("nama", mahasiswa.getNama());
                model.addAttribute("judul", mahasiswa.getJudul());
            }
            session.setAttribute("npm", npm);
            model.addAttribute("npm", npm);
        }


        // daftar komponen nilai
        List<KomponenNilai> komponenNilai = pengujiNilaiRepository.getKomponen(npm,peran);
        model.addAttribute("komponenNilai", komponenNilai);
        return "dosen/nilaiMahasiswa"; 
    }

    // menyimpan nilai mahasiswa
    @PostMapping("/savenilai")
        public String saveNilai(@RequestParam("deskripsi") List<String> deskripsi,
                                @RequestParam("nilai") List<Double> nilai,
                                @RequestParam(value = "npm", required = false) String npm,
                                @RequestParam(value = "peran", required = false) String peran,
                                @RequestParam(value ="id_ta", required = false) int id_ta,
                                @RequestParam(value ="id_komp", required = false) int id_komp,
                                Model model, HttpSession session ) {
            

            double totalNilaiAkhir = 0.0;
            for (int i = 0; i < deskripsi.size(); i++) {
                String komponenDeskripsi = deskripsi.get(i);
                double nilaiInput = nilai.get(i);
                double nilaiAkhir = 0.0;

                // ngambil bobot berdasarkan deskripsi dan peran
                List<Bobot> bobotList = jdbdNilai.getBobot(komponenDeskripsi, peran);

                if (!bobotList.isEmpty()) {
                    int bobot = bobotList.get(0).getBobot();

                    // ngambil id_komp untuk setiap deskripsi
                    List<Integer> idKompList = jdbdNilai.getIdKompList(id_ta);

                    if (i < idKompList.size()) {
                        id_komp = idKompList.get(i);
                        nilaiAkhir = nilaiInput * bobot / 100.0;

                        // simpan nilai ke tabel nilai_ta
                        jdbdNilai.saveNilaiToNilai_TA(jdbdNilai.getIdTa(npm),id_komp, peran, nilaiAkhir);

                        // tambah nilai akhir ke total
                        totalNilaiAkhir += nilaiAkhir;
                    } else {
                        System.out.println("Id komponen tidak ditemukan untuk deskripsi: " + komponenDeskripsi);
                    }
                }
            }

            // simpan total nilai akhir ke tabel tugas_akhir
            jdbdNilai.saveNilai(totalNilaiAkhir, npm, peran);

            model.addAttribute("message", "Nilai berhasil disimpan.");
            return "redirect:/dosen/DetailJadwal?npm=" + npm + "&peran=" + peran;
    }

}