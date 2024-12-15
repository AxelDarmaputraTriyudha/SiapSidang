package com.RPL.SiapSidang.BAP;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BAP {
    private String jenis;
    private String nama;
    private String judul;
    private String npm;
    private String semester;
    private int tahun;
    private String waktu;
    private String tanggal;
    private String tempat;
    private String nik_dosen;
    private String nama_dosen;
    private String peran;
    private Double nilai_pb;
    private Double nilai_pu1;
    private Double nilai_pu2;
    private Double nilai_koord;
    private Boolean status_bap;
    private int id_sidang;
}
