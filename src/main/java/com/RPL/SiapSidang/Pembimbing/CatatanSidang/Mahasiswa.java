package com.RPL.SiapSidang.Pembimbing.CatatanSidang;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Mahasiswa {
    private String npm;
    private String nama;
    private String judul;
    private int id_ta;
}
