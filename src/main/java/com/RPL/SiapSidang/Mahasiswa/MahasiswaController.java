package com.RPL.SiapSidang.Mahasiswa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RPL.SiapSidang.RequiredRole;
import com.RPL.SiapSidang.Koord.Home.DataSidang;
import com.RPL.SiapSidang.Pembimbing.CatatanSidang.CatatanSidang;
import com.RPL.SiapSidang.Pembimbing.CatatanSidang.Mahasiswa;
import com.RPL.SiapSidang.Pembimbing.CatatanSidang.PembimbingCatatanRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mahasiswa")
public class MahasiswaController {
    @Autowired
    JDBCMahasiswaRepository mahasiswaRepository;

    @Autowired
    private PembimbingCatatanRepo pembimbingCatatanRepo;

    @GetMapping("/home")
    @RequiredRole("mahasiswa")
    public String index(Model model,
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(required = false, defaultValue = "") LocalDate tgl_awal,
            @RequestParam(required = false, defaultValue = "") LocalDate tgl_akhir,
            @RequestParam(required = false, defaultValue = "") String semester,
            @RequestParam(required = false, defaultValue = "0") int tahun,
            HttpSession session){
        
        String npm = (String) session.getAttribute("npm"); 
        List<DataSidang> dataSidangList = mahasiswaRepository.findAll(tgl_awal, tgl_akhir, semester, tahun, npm);

        model.addAttribute("tgl_awal", tgl_awal);
        model.addAttribute("tgl_akhir", tgl_akhir);
        model.addAttribute("sidang", dataSidangList);
        model.addAttribute("semester", semester);
        model.addAttribute("tahun", tahun);
        return "/mahasiswa/index";
    }

    @GetMapping("/DetailJadwal")
    @RequiredRole("mahasiswa")
    public String index(Model model,
            @RequestParam(required = true) String npm,
            HttpSession session){
        List<DataSidang> data = mahasiswaRepository.findDetail(npm);

        model.addAttribute("data", data.get(3));

        // list nama dosen
        String penguji1 = "", penguji2 = "", pembimbing = "";
        
        // assign nama dosen sesuai peran
        for (int i = 0; i<data.size(); i++){
            DataSidang dataNow = data.get(i);
            switch (dataNow.getPeran()) {
                case "PB1":
                    pembimbing = dataNow.getNama_dosen();
                    break;
                case "PU1":
                    penguji1 = dataNow.getNama_dosen();
                    break;
                case "PU2":
                    penguji2 = dataNow.getNama_dosen();
                    break;
                default:
                    break;
            }
        }

        // add nama-nama dosen
        model.addAttribute("nama_pu1", penguji1);
        model.addAttribute("nama_pu2", penguji2);
        model.addAttribute("nama_pb", pembimbing);
        model.addAttribute("npm", npm);

        return "/mahasiswa/DetailJadwal/detailJadwal";
    }

    @GetMapping("/nilai")
    @RequiredRole("mahasiswa")
    public String nilaiView(Model model,
                            HttpSession session){

        String npm = (String)session.getAttribute("npm");
        List<DataSidang> data = mahasiswaRepository.findDetail(npm);

        // list nama dosen
        String koordinator = "", penguji1 = "", penguji2 = "", pb1 = "", pb2 = "-";
        
        // assign nama dosen sesuai peran
        for (int i = 0; i<data.size(); i++){
            DataSidang dataNow = data.get(i);
            switch (dataNow.getPeran()) {
                case "Koordinator":
                    koordinator = dataNow.getNama_dosen();
                    break;
                case "PB1":
                    pb1 = dataNow.getNama_dosen();
                    break;
                case "PB2":
                    pb2 = dataNow.getNama_dosen();
                    break;
                case "PU1":
                    penguji1 = dataNow.getNama_dosen();
                    break;
                case "PU2":
                    penguji2 = dataNow.getNama_dosen();
                    break;
                default:
                    break;
            }
        }

        // add nama-nama dosen
        model.addAttribute("koordinator", koordinator);
        model.addAttribute("nama_pu1", penguji1);
        model.addAttribute("nama_pu2", penguji2);
        model.addAttribute("nama_pb1", pb1);
        model.addAttribute("nama_pb2", pb2);
        model.addAttribute("npm", npm);

        // add nilai
        NilaiMahasiswa nilaiMahasiswaList = mahasiswaRepository.findNilai(npm).get(0);

        // cari nilai akhir
        double koordAkhir = nilaiMahasiswaList.getNilaiAkhirKoordinator()*0.1;
        double pengujiAkhir = nilaiMahasiswaList.getNilaiAkhirPU1()*0.35;
        double anggotaPengujiAkhir = nilaiMahasiswaList.getNilaiAkhirPU2()*0.35;
        double pembimbingAkhir = nilaiMahasiswaList.getNilaiAkhirPembimbing1()*0.2;
        double nilaiAkhir = koordAkhir + pengujiAkhir + anggotaPengujiAkhir + pembimbingAkhir;

        // log nilai akhir
        System.out.println("Nilai akhir: " + nilaiAkhir);

        nilaiMahasiswaList.setNilaiAkhir(nilaiAkhir);

        int id_ta = nilaiMahasiswaList.getId_ta();
        model.addAttribute("nilaiMahasiswaList", nilaiMahasiswaList);

        // update nilai akhir dan angka akhir di database
        if(koordAkhir > 0 && pengujiAkhir > 0 && anggotaPengujiAkhir > 0 && pembimbingAkhir > 0){
            // update nilai akhir mahasiswa
            mahasiswaRepository.updateNilaiAkhir(nilaiAkhir, id_ta);

            // update angka akhir
            String angkaAkhir = convertNilai(nilaiAkhir);
            // log angka akhir
            System.out.println("Angka akhir: "+ angkaAkhir);
            mahasiswaRepository.updateAngkaAkhir(angkaAkhir, id_ta);
        }

        model.addAttribute("koordAkhir", koordAkhir);
        model.addAttribute("pengujiAkhir", pengujiAkhir);
        model.addAttribute("anggotaPengujiAkhir", anggotaPengujiAkhir);
        model.addAttribute("pembimbingAkhir", pembimbingAkhir);
        model.addAttribute("a", nilaiMahasiswaList);
        return "/mahasiswa/nilai/nilaiMhs";
    }

    @GetMapping("/catatanSidang")
    @RequiredRole("mahasiswa")
    public String viewCatatan(HttpSession session, Model model){
        String npm = (String) session.getAttribute("npm");
        Mahasiswa mahasiswa = pembimbingCatatanRepo.getMahasiswaByNpm(npm);

        if (mahasiswa != null) {
            model.addAttribute("nama", mahasiswa.getNama());
            model.addAttribute("judul", mahasiswa.getJudul());
            model.addAttribute("npm", npm);
        }

        CatatanSidang catatanSidang = pembimbingCatatanRepo.getCatatan(npm).get(0);
        if(catatanSidang == null){
            model.addAttribute("lastCatatanSidang", "");
        }
        else{
            model.addAttribute("lastCatatanSidang", catatanSidang.getCatatan());
        }
        return "/mahasiswa/Catatan/catatanSidang";
    }

    private String convertNilai(double score){
        if (score >= 80 && score <= 100) {
            return "A";
        } else if (score >= 77 && score < 80) {
            return "A-";
        } else if (score >= 73 && score < 77) {
            return "B+";
        } else if (score >= 70 && score < 73) {
            return "B";
        } else if (score >= 67 && score < 70) {
            return "B-";
        } else if (score >= 63 && score < 67) {
            return "C+";
        } else if (score >= 60 && score < 63) {
            return "C";
        } else if (score >= 50 && score < 60) {
            return "D";
        } else if (score >= 0 && score < 50) {
            return "E";
        } else {
            return "-";
        }
    }
}
