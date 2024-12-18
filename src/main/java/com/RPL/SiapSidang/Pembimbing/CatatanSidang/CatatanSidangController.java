package com.RPL.SiapSidang.Pembimbing.CatatanSidang;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dosen")
public class CatatanSidangController {

    @Autowired
    private PembimbingCatatanRepo pembimbingCatatanRepo;

    // Menampilkan halaman catatan sidang mahasiswa
    @GetMapping("/CatatanSidang/catatan")
    public String showCatatanPage(Model model,
                            @RequestParam(value = "npm") String npm,
                            @RequestParam(value = "peran") String peran,
                            HttpSession session) {

        if (npm == null || peran == null) {
            throw new IllegalArgumentException("NPM atau Peran tidak ditemukan");
        }

        // Mengambil data mahasiswa berdasarkan NPM
        Mahasiswa mahasiswa = pembimbingCatatanRepo.getMahasiswaByNpm(npm);
        if (mahasiswa != null) {
            model.addAttribute("nama", mahasiswa.getNama());
            model.addAttribute("judul", mahasiswa.getJudul());
            model.addAttribute("npm", npm);
            model.addAttribute("peran", peran);

            session.setAttribute("npmCatatan", npm);
            session.setAttribute("peranCatatan", peran);
        }

        // Ambil semua catatan sidang
        List<CatatanSidang> catatanSidang = pembimbingCatatanRepo.getCatatan(npm);
        model.addAttribute("catatanSidang", catatanSidang);

        // Jika ada catatan sidang, ambil catatan terakhir
        if (!catatanSidang.isEmpty()) {
            String lastCatatan = catatanSidang.get(catatanSidang.size() - 1).getCatatan(); // Catatan terakhir
            model.addAttribute("lastCatatanSidang", lastCatatan);
        } else {
            model.addAttribute("lastCatatanSidang", ""); // Jika tidak ada catatan
        }

        System.out.println(session.getAttribute("peran"));

        return "pembimbing/CatatanSidang/catatan";
    }

    @PostMapping("/CatatanSidang/save")
    public String saveCatatan(
            @RequestParam("catatanSidang") String catatan,
            Model model, HttpSession session) {

        String npm = (String) session.getAttribute("npmCatatan");
        String peran = (String) session.getAttribute("peranCatatan");

        List<CatatanSidang> catatanSidang = pembimbingCatatanRepo.getCatatan(npm);
        String lastCatatan = catatanSidang.isEmpty() ? "" : catatanSidang.get(catatanSidang.size() - 1).getCatatan();

        if (catatan.trim().equals(lastCatatan)) {
            model.addAttribute("errorMessage", "Catatan tidak berubah!");
            return "redirect:/dosen/CatatanSidang/catatan?npm=" + npm + "&peran=" + peran;
        }

        pembimbingCatatanRepo.saveCatatanSidang(npm, catatan);

        return "redirect:/dosen/CatatanSidang/catatan?npm=" + npm + "&peran=" + peran;
    }

    
    @PostMapping("/back")
    public String back(HttpSession session){
        String npm = (String) session.getAttribute("npmCatatan");
        String peran = (String) session.getAttribute("peranCatatan");
        
        return "redirect:/dosen/DetailJadwal?npm=" + npm + "&peran=" + peran;
    }
}
