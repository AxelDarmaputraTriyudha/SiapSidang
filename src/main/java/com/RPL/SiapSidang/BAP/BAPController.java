package com.RPL.SiapSidang.BAP;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.core.io.Resource;

import com.RPL.SiapSidang.RequiredRole;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/koord")
public class BAPController {
    @Autowired
    private JDBCBAP jdbcbap;

    @Autowired
    private PDFController pdfController;

    @GetMapping("/generateBAP/{npm}")
    public String index(@PathVariable String npm, Model model){
        model.addAttribute("npm", npm);

        List<BAP> data = jdbcbap.findData(npm);

        model.addAttribute("data", data.get(3));

        // list nama dosen
        String penguji1 = "", penguji2 = "", pembimbing1 = "", pembimbing2 = "", koord = "";
        Double nilaiPU1 = 0.0, nilaiPU2 = 0.0, nilaiPB = 0.0, nilaiKoord = 0.0;
        
        // assign nama dosen sesuai peran
        for (int i = 0; i<data.size(); i++){
            BAP dataNow = data.get(i);
            switch (dataNow.getPeran()) {
                case "PB1":
                    pembimbing1 = dataNow.getNama_dosen();
                    nilaiPB = dataNow.getNilai_pb();
                    break;
                case "PB2":
                    pembimbing2 = dataNow.getNama_dosen();
                    break;
                case "PU1":
                    penguji1 = dataNow.getNama_dosen();
                    nilaiPU1 = dataNow.getNilai_pu1();
                    break;
                case "PU2":
                    penguji2 = dataNow.getNama_dosen();
                    nilaiPU2 = dataNow.getNilai_pu2();
                    break;
                case "Koordinator":
                    koord = dataNow.getNama_dosen();
                    nilaiKoord = dataNow.getNilai_koord();
                    break;
                default:
                    break;
            }
        }

        NilaiBAP nilaiBAP = new NilaiBAP(penguji1, nilaiPU1, (nilaiPU1 * 35/100), penguji2, nilaiPU2, (nilaiPU2 * 30/100), pembimbing1, nilaiPB, (nilaiPB * 20/100), koord, nilaiKoord, (nilaiKoord * 10/100), pembimbing2, 0.0);
        nilaiBAP.setNilaiAkhir((nilaiPU1 * 35/100) + (nilaiPU2 * 30/100) + (nilaiPB * 20/100) + (nilaiKoord * 10/100));
        model.addAttribute("nilaiBAP", nilaiBAP);

        model.addAttribute("statusBAP", data.get(0).getStatus_bap());
        System.out.println(model.getAttribute("statusBAP"));

        return "/bap/index";
    }

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/uploadBAP/{npm}")
    @RequiredRole("koordinator")
    public String uploadPDF(
            @PathVariable String npm,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        try {
            // Validasi file
            if (file.isEmpty() || !file.getOriginalFilename().endsWith(".pdf")) {
                redirectAttributes.addFlashAttribute("errorMessage", "File harus berupa PDF!");
                return "redirect:/koord/generateBAP/" + npm; // Redirect ke halaman sebelumnya
            }

            // Membuat nama file dengan format BAP_NPM_timestamp.pdf
            String timestamp = String.valueOf(System.currentTimeMillis());
            String fileName = "BAP_" + npm + "_" + timestamp + ".pdf";

            // Mendefinisikan direktori penyimpanan (root folder 'upload')
            String uploadDir = "upload";
            Path uploadPath = Paths.get(uploadDir);

            // Membuat direktori jika belum ada
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Menyimpan file ke direktori dengan nama baru
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Redirect ke halaman sebelumnya dengan notifikasi sukses
            redirectAttributes.addFlashAttribute("successMessage", "PDF berhasil diunggah.");
            return "redirect:/koord/generateBAP/" + npm;
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Terjadi kesalahan saat mengunggah file!");
            return "redirect:/koord/generateBAP/" + npm;
        }
    }

    @PostMapping("/downloadBAP/{npm}")
    @RequiredRole("koordinator")
    public ResponseEntity<?> downloadBAP(@PathVariable String npm, HttpServletResponse response, Model model) {
        try {
            // Lokasi folder upload
            String uploadDir = "upload";
            Path uploadPath = Paths.get(uploadDir);

            // Pastikan direktori ada
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Cek status dari BAP apakah sudah di-generate
            BAP currBAP = jdbcbap.findData(npm).get(0);
            if (currBAP.getStatus_bap() == null || !currBAP.getStatus_bap()) {
                return ResponseEntity.badRequest().body("BAP belum di-generate!");
            }

            // Cari file terbaru berdasarkan NPM
            Optional<Path> latestFile = Files.list(uploadPath)
                    .filter(file -> file.getFileName().toString().startsWith("BAP_" + npm))
                    .filter(file -> file.getFileName().toString().endsWith(".pdf"))
                    .max(Comparator.comparingLong(file -> file.toFile().lastModified()));

            // Jika file tidak ditemukan, kirimkan pesan error
            if (latestFile.isEmpty()) {
                pdfController.generatePdf(npm, response, model);
                return null;
            }

            // Ambil file terbaru
            Path filePath = latestFile.get();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.badRequest().body("File tidak dapat diakses!");
            }

            // Tetapkan nama file baru untuk unduhan
            String downloadFileName = "BAP_" + npm + ".pdf";

            // Kembalikan file sebagai respons download
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + downloadFileName + "\"")
                    .body(resource);

        } catch (Exception e) {
            // Tangkap error lainnya dan kembalikan pesan error
            return ResponseEntity.internalServerError().body("Terjadi kesalahan saat mengunduh file: " + e.getMessage());
        }
    }

    // @PostMapping("/downloadBAP/{npm}")
    // @RequiredRole("koordinator")
    // public ResponseEntity<Resource> downloadBAP(@PathVariable String npm, HttpServletResponse response, RedirectAttributes redirectAttributes) {
    //     try {
    //         // Lokasi folder upload
    //         String uploadDir = "upload";
    //         Path uploadPath = Paths.get(uploadDir);

    //         // Pastikan direktori ada
    //         if (!Files.exists(uploadPath)) {
    //             throw new RuntimeException("Direktori upload tidak ditemukan");
    //         }

    //         // Cek status dari BAP nya udah di generate apa belum
    //         BAP currBAP = jdbcbap.findData(npm).get(0);
    //         if(!currBAP.getStatus_bap() || currBAP.getStatus_bap() == null){
    //             System.out.println("masuk sini");
    //             redirectAttributes.addFlashAttribute("errorMessage", "BAP Belum di Generate!");
    //         }

    //         // Cari file terbaru berdasarkan NPM
    //         Optional<Path> latestFile = Files.list(uploadPath)
    //                 .filter(file -> file.getFileName().toString().startsWith("BAP_" + npm + "_")) // Filter berdasarkan nama
    //                 .filter(file -> file.getFileName().toString().endsWith(".pdf"))              // Hanya file PDF
    //                 .max(Comparator.comparingLong(file -> file.toFile().lastModified()));        // Ambil file terbaru

    //         if (latestFile.isEmpty()) {
    //             pdfController.generatePdf(npm, response);
    //             return null;
    //         }

    //         // Ambil file terbaru
    //         Path filePath = latestFile.get();
    //         Resource resource = new UrlResource(filePath.toUri());

    //         if (!resource.exists() || !resource.isReadable()) {
    //             throw new RuntimeException("File tidak ditemukan atau tidak dapat diakses");
    //         }

    //         // Tetapkan nama file baru untuk unduhan (tanpa timestamp)
    //         String downloadFileName = "BAP_" + npm + ".pdf";

    //         // Kembalikan file sebagai respons download dengan nama baru
    //         return ResponseEntity.ok()
    //                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + downloadFileName + "\"")
    //                 .body(resource);

    //     } catch (Exception e) {
    //         throw new RuntimeException("Terjadi kesalahan saat mengunduh file: " + e.getMessage(), e);
    //     }
    // }
}
