package com.RPL.SiapSidang.Koord.Home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/koord")
public class HomepageController {
    @Autowired
    JDBCDataSidangImplementation dataSidangRepo;

    @GetMapping("/home")
    public String index(Model model,
            @RequestParam(required = false, defaultValue = "") String filter){

        List<DataSidang> dataSidangList = null;
        if(filter.length() == 0){
            dataSidangList = dataSidangRepo.findAll();
        }
        else{
            dataSidangList = dataSidangRepo.findWithFilter(filter);
        }
        model.addAttribute("filter", filter);
        model.addAttribute("sidang", dataSidangList);
        return "/koord/home/tabelJadwal";
    }
}
