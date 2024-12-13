package com.RPL.SiapSidang.Dosen.DetailJadwal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RPL.SiapSidang.RequiredRole;
import com.RPL.SiapSidang.Koord.Home.DataSidang;

@Controller
@RequestMapping("/dosen")
public class DetailSidangDosenController {
    @Autowired
    JDBCDetailJadwalDosenImplementation detailJadwalDosenRepo;

    @GetMapping("/DetailJadwal")
    @RequiredRole("Dosen")
    public String index(Model model,
            @RequestParam(required = true) String npm,
            @RequestParam String peran){

        List<DataSidang> data = detailJadwalDosenRepo.findData(npm);

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
        model.addAttribute("peran", peran);
        model.addAttribute("npm", npm);

        return "/dosen/DetailJadwal/detailJadwal";
    }
}