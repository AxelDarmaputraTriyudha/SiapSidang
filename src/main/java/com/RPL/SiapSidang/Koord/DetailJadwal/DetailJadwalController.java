package com.RPL.SiapSidang.Koord.DetailJadwal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RPL.SiapSidang.Koord.Home.DataSidang;

@Controller
@RequestMapping("/koord")
public class DetailJadwalController {
    @Autowired
    JDBCDetailJadwalImplementation detailJadwalRepo;

    @GetMapping("/DetailJadwal")
    public String index(Model model,
            @RequestParam(required = true) String npm){

        DataSidang data = detailJadwalRepo.findData(npm).get(0);
        model.addAttribute("data", data);
        return "/koord/DetailJadwal/index";
    }
}
