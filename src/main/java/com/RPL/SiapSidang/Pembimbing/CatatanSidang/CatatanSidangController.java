package com.RPL.SiapSidang.Pembimbing.CatatanSidang;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pembimbing")
public class CatatanSidangController {

    @Autowired
    private PembimbingCatatanRepo pembimbingCatatanRepo;

    @Autowired
    private JDBCCatatan jdbcCatatan;

    // Menampilkan halaman catatan sidang mahasiswa
    @GetMapping("/CatatanSidang/catatan")
    public String showCatatanPage(Model model, HttpSession session,
                              @RequestParam(value = "npm", required = false) String npm,
                              @RequestParam(value = "peran", required = false) String peran) {
    
        if (npm == null || peran == null) {
            throw new IllegalArgumentException("NPM atau Peran tidak ditemukan");
        }

        // Mengambil data mahasiswa berdasarkan NPM
        Mahasiswa mahasiswa = jdbcCatatan.getMahasiswaByNpm(npm);
        if (mahasiswa != null) {
            model.addAttribute("nama", mahasiswa.getNama());
            model.addAttribute("judul", mahasiswa.getJudul());
            model.addAttribute("npm", npm);
        }

        System.out.println("Model attributes: ");
        System.out.println("Nama: " + model.getAttribute("nama"));
        System.out.println("NPM: " + model.getAttribute("npm"));
        System.out.println("Judul: " + model.getAttribute("judul"));

        List<CatatanSidang> catatanSidang = pembimbingCatatanRepo.getCatatan(npm);
        model.addAttribute("catatanSidang", catatanSidang);

        return "pembimbing/CatatanSidang/catatan";
    }

    @PostMapping("/CatatanSidang/save")
    public String saveCatatan(
            @RequestParam("catatan") String catatan,
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "peran", required = false) String peran,
            Model model,
            HttpSession session) {
    
        // Simpan npm dan peran ke session jika ada
        if (npm != null) {
            session.setAttribute("npm", npm);
        }
        if (peran != null) {
            session.setAttribute("peran", peran);
        }
    
        // Ambil npm dan peran dari session
        npm = (String) session.getAttribute("npm");
        peran = (String) session.getAttribute("peran");
    
        // Validasi npm dan peran
        if (npm == null || peran == null) {
            model.addAttribute("showAlert", true);
            model.addAttribute("alertMessage", "NPM atau peran tidak ditemukan.");
            return "redirect:/pembimbing/CatatanSidang/catatan?npm=" + npm + "&peran=" + peran;
        } else if (catatan == null || catatan.isEmpty()) {
            model.addAttribute("showAlert", true);
            model.addAttribute("alertMessage", "Catatan sidang tidak boleh kosong.");
            return "redirect:/pembimbing/CatatanSidang/catatan?npm=" + npm + "&peran=" + peran;
        } else {
            // Simpan catatan sidang
            jdbcCatatan.saveCatatanSidang(npm, catatan);
        }
    
        // Redirect ke halaman catatan sidang dengan npm dan peran sebagai query parameters
        return "redirect:/pembimbing/CatatanSidang/catatan?npm=" + npm + "&peran=" + peran;
    }
}
