package com.RPL.SiapSidang.Koord.BuatJadwal;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sidang {
    private int id_sidang;
    private String nik;
    private int id_ta;
    private String peran;
    private String hari;
    private LocalDate tanggal;
    private LocalTime waktu;
    private String tempat;
}