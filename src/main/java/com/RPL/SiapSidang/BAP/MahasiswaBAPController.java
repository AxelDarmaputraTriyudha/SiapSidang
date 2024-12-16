package com.RPL.SiapSidang.BAP;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
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

@Controller
@RequestMapping ("/mahasiswa")
public class MahasiswaBAPController {
    @Autowired
    private JDBCBAP jdbcbap;

    @GetMapping("/generateBAP/{npm}")
    @RequiredRole("mahasiswa")
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

        DecimalFormat df = new DecimalFormat("#.00");
        double naPU1 = Double.parseDouble(df.format((nilaiPU1 * 35/100)));
        double naPU2 = Double.parseDouble(df.format((nilaiPU2 * 35/100)));
        double naPB = Double.parseDouble(df.format((nilaiPB * 20/100)));
        double naKoord = Double.parseDouble(df.format((nilaiKoord * 10/100)));

        NilaiBAP nilaiBAP = new NilaiBAP(penguji1, nilaiPU1, naPU1, penguji2, nilaiPU2, naPU2, pembimbing1, nilaiPB, naPB, koord, nilaiKoord, naKoord, pembimbing2, 0.0);
        double tempNA = naPU1 + naPU2 + naPB + naKoord;
        double naAkhir = Double.parseDouble(df.format(tempNA));
        
        nilaiBAP.setNilaiAkhir(naAkhir);
        model.addAttribute("nilaiBAP", nilaiBAP);

        model.addAttribute("statusBAP", data.get(0).getStatus_bap());

        return "/bap/mahasiswa";
    }

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/uploadBAP/{npm}")
    @RequiredRole("mahasiswa")
    public String uploadPDF(
            @PathVariable String npm,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        try {
            // Validasi file kalau salah format
            if (file.isEmpty() || !file.getOriginalFilename().endsWith(".pdf")) {
                redirectAttributes.addFlashAttribute("errorMessage", "File harus berupa PDF!");
                return "redirect:/mahasiswa/generateBAP/" + npm; // Redirect ke halaman sebelumnya
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
            return "redirect:/mahasiswa/generateBAP/" + npm;
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Terjadi kesalahan saat mengunggah file!");
            return "redirect:/mahasiswa/generateBAP/" + npm;
        }
    }

    @PostMapping("/downloadBAP/{npm}")
    @RequiredRole("mahasiswa")
    public ResponseEntity<?> downloadBAP(@PathVariable String npm) {
        try {
            String uploadDir = "upload";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Mengambil file yang paling terakhir dengan mengurutkan berdasarkan timestamp dan NPM yang sesuai
            Optional<Path> latestFile = Files.list(uploadPath)
                    .filter(file -> file.getFileName().toString().startsWith("BAP_" + npm))
                    .filter(file -> file.getFileName().toString().endsWith(".pdf"))
                    .max(Comparator.comparingLong(file -> file.toFile().lastModified()));

            // Kalau belum ada yang upload sama sekali
            if (latestFile.isEmpty()) {
                return ResponseEntity.badRequest().body("BAP belum diupload oleh Koordinator");
            }

            Path filePath = latestFile.get();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.badRequest().body("File tidak dapat diakses!");
            }

            // Proses download file BAP yang terbaru
            String downloadFileName = "BAP_" + npm + ".pdf";
            return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF) // Tetapkan Content-Type
            .header("Content-Disposition", "attachment; filename=" + downloadFileName)
            .body(resource);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Terjadi kesalahan saat mengunduh file: " + e.getMessage());
        }
    }
}
