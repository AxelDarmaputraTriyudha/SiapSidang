package com.RPL.SiapSidang.Penguji.NilaiMahasiswa;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class KomponenNilai {
    private int id_ta;
    private int id_komp;
    private String deskripsi;
    private int nilai;
}