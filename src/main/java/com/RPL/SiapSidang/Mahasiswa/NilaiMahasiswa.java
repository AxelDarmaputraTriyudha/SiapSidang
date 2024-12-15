package com.RPL.SiapSidang.Mahasiswa;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NilaiMahasiswa {
    private int id_ta;
    private String id_mahasiswa;
    private String judul;
    private double nilaiAkhirPembimbing1;
    private double nilaiAkhirPU1;   
    private double nilaiAkhirPU2;   
    private double nilaiAkhirKoordinator;
    private double nilaiAkhir;   
}
