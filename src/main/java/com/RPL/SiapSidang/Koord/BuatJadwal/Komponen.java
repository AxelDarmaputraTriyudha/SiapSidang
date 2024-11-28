package com.RPL.SiapSidang.Koord.BuatJadwal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Komponen {
    private int id_komp;
    private String deskripsi;
    private String peran;
    private int bobot;
}
