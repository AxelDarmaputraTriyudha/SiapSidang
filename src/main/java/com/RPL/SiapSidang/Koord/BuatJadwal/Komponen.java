package com.RPL.SiapSidang.Koord.BuatJadwal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Komponen {
    private String deskripsi;
    private int bobot_ketua_penguji;
    private int bobot_anggota_penguji;
    private int bobot_pembimbing;
}
