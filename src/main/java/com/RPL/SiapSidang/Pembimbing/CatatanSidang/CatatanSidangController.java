package com.RPL.SiapSidang.Pembimbing.CatatanSidang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/pembimbing/catatan")
public class CatatanSidangController {

    @Autowired
    private JDBCCatatan jdbcCatatan;

    @GetMapping("/data")
    public Map<String, Object> getData(@RequestParam int idTugasAkhir) {
        return jdbcCatatan.findDataByIdTugasAkhir(idTugasAkhir);
    }

    @PostMapping("/save")
    public String saveCatatan(@RequestParam int idTugasAkhir, @RequestBody String catatan) {
        jdbcCatatan.saveCatatan(idTugasAkhir, catatan);
        return "Catatan berhasil disimpan.";
    }
}
