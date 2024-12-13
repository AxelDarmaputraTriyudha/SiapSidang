package com.RPL.SiapSidang.BAP;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/koord")
public class BAPController {
    @Autowired
    private JDBCBAP jdbcbap;

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

        return "/bap/index";
    }
}
