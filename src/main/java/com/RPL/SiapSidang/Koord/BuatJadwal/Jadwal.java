package com.RPL.SiapSidang.Koord.BuatJadwal;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Jadwal {
    private Date tanggal;
    private Time waktu;
    private String tempat;
}
