package com.RPL.SiapSidang.Koord.BuatJadwal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
@RequestMapping("/koord")
public class KoordController {
    @Autowired
    private KoordRepository repo;
    private String semester;
    private String tahun;
    private Sidang koord;
    private Sidang penguji1;
    private Sidang penguji2;
    private Sidang pembimbing1;
    private Sidang pembimbing2;
    private TA currTA;
    
    @GetMapping("/buatJadwal1")
    public String buatJadwal1(HttpSession session, Model model) {
        model.addAttribute("namaMahasiswa", session.getAttribute("namaMahasiswa"));
        model.addAttribute("npm", session.getAttribute("npm"));
        model.addAttribute("semester", session.getAttribute("semester"));
        model.addAttribute("tahun", session.getAttribute("tahun"));
        model.addAttribute("tgl", session.getAttribute("tgl"));
        model.addAttribute("waktu", session.getAttribute("waktu"));
        model.addAttribute("tempat", session.getAttribute("tempat"));
        return "koord/BuatJadwal/index1";
    }

    @PostMapping("/buatJadwal1")
    public String buatJadwal11(
        @RequestParam("namaMahasiswa") String namaMahasiswa,
        @RequestParam("npm") String npm,
        @RequestParam("semester") String semester,
        @RequestParam("tahun") String tahun,
        @RequestParam("tgl") LocalDate tgl,
        @RequestParam("waktu") String waktu,
        @RequestParam("tempat") String tempat, 
        HttpSession session
        ){
            session.setAttribute("namaMahasiswa", namaMahasiswa);
            session.setAttribute("npm", npm);
            session.setAttribute("semester", semester);
            session.setAttribute("tahun", tahun);
            session.setAttribute("tgl", tgl);
            session.setAttribute("waktu", waktu);
            session.setAttribute("tempat", tempat);
            this.semester = (String) session.getAttribute("semester");
            this.tahun = (String) session.getAttribute("tahun");
            return "redirect:/koord/buatJadwal2";
        }

    @GetMapping("/buatJadwal2")
    public String buatJadwal2(HttpSession session, Model model) {
        // Ambil data dari sesi
        String pb1 = (String) session.getAttribute("pb1");
        String pb2 = (String) session.getAttribute("pb2");
        String pu1 = (String) session.getAttribute("pu1");
        String pu2 = (String) session.getAttribute("pu2");
        
        // Menambahkan data dosen yang diambil dari repositori ke model
        List<Dosen> list = this.repo.getAllDosen();
        model.addAttribute("pb1List", list);
        model.addAttribute("pb2List", list);
        model.addAttribute("pu1List",list);
        model.addAttribute("pu2List", list);
        
        // Menambahkan data yang tersimpan di sesi ke model agar tetap ditampilkan di form
        model.addAttribute("selectedPb1", pb1);
        model.addAttribute("selectedPb2", pb2);
        model.addAttribute("selectedPu1",pu1);
        model.addAttribute("selectedPu2", pu2);
        
        return "koord/BuatJadwal/index2";
    }                

    @PostMapping("/buatJadwal2")
    public String buatJadwal21(
        @RequestParam("pb1") String pb1,
        @RequestParam("pb2") String pb2,
        @RequestParam("pu1") String pu1,
        @RequestParam("pu2") String pu2,
        HttpSession session) {
        
        // Menyimpan data yang dipilih ke sesi
        session.setAttribute("pb1", pb1);
        session.setAttribute("pb2", pb2);
        session.setAttribute("pu1", pu1);
        session.setAttribute("pu2", pu2);

        // Redirect ke halaman selanjutnya
        return "redirect:/koord/buatJadwal3";
    }        

    @GetMapping("/buatJadwal3")
    public String buatJadwal3(Model model, HttpSession session){
        List<Komponen> list = this.repo.getAllKomponen(this.semester, this.tahun);
        model.addAttribute("nilaiList", list);
        this.currTA = this.repo.getTA((String) session.getAttribute("npm"));
        model.addAttribute("npmMahasiswa", currTA.getNpm());
        model.addAttribute("namaMahasiswa", currTA.getNama());
        model.addAttribute("judul", currTA.getJudul());

        return "koord/BuatJadwal/index3";
    }

    @PostMapping("/buatJadwal3")
    public String buatJadwal31(HttpSession session){
        String waktu = (String) session.getAttribute("waktu") + ":00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime waktuTime = LocalTime.parse(waktu, formatter);

        // BAGIAN KOORDINATOR
        this.koord = new Sidang(
            0, 
            (String) session.getAttribute("nik"), 
            this.currTA.getId_ta(), 
            "Koordinator", 
            "hari", 
            (LocalDate) session.getAttribute("tgl"), 
            waktuTime, 
            (String) session.getAttribute("tempat"));

        // BAGIAN PENGUJI 1
        this.penguji1 = new Sidang(
            0, 
            (String) session.getAttribute("pu1"), 
            this.currTA.getId_ta(), 
            "PU1", 
            "hari", 
            (LocalDate) session.getAttribute("tgl"), 
            waktuTime, 
            (String) session.getAttribute("tempat"));

        // BAGIAN PENGUJI 2
        this.penguji2 = new Sidang(
            0, 
            (String) session.getAttribute("pu2"), 
            this.currTA.getId_ta(), 
            "PU2", 
            "hari", 
            (LocalDate) session.getAttribute("tgl"), 
            waktuTime, 
            (String) session.getAttribute("tempat"));

        // BAGIAN PEMBIMBING 1
        this.pembimbing1 = new Sidang(
            0, 
            (String) session.getAttribute("pb1"), 
            this.currTA.getId_ta(), 
            "PB1", 
            "hari", 
            (LocalDate) session.getAttribute("tgl"), 
            waktuTime, 
            (String) session.getAttribute("tempat"));

        // BAGIAN PEMBIMBING 2
        if (!String.valueOf("-").equals(session.getAttribute("pb2"))){
            this.pembimbing2 = new Sidang(
            0, 
            (String) session.getAttribute("pb2"), 
            currTA.getId_ta(), 
            "PB2", 
            "hari", 
            (LocalDate) session.getAttribute("tgl"), 
            waktuTime, 
            (String) session.getAttribute("tempat"));
        }
        this.repo.setJadwal(koord, penguji1, penguji2, pembimbing1, pembimbing2);

        session.invalidate();

        return "redirect:/koord/home";
    }
}
