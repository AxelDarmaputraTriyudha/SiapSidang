package com.RPL.SiapSidang.Koord.Home;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataSidang {
    private String jenis;
    private String nama;
    private String semester;
    private int tahun;
    private String waktu;
    private String tanggal;
}
