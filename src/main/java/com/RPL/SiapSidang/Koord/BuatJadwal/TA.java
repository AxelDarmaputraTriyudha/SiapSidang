package com.RPL.SiapSidang.Koord.BuatJadwal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TA {
    private int id_ta;
    private String npm;
    private String nama;
    private String judul;
    private String semester;
    private int tahun;
}
