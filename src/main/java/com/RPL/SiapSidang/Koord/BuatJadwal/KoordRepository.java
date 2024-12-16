package com.RPL.SiapSidang.Koord.BuatJadwal;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public interface KoordRepository {
    List<Dosen> getAllDosen();
    List<Komponen> getAllKomponen(String semester, int tahun);
    TA getTA(String npm);
    void setJadwal(Sidang koord, Sidang penguji1, Sidang penguji2, Sidang pembimbing1, Sidang pembimbing2);
    List<Jadwal> getJadwal(LocalDate tanggal, Time waktu, String tempat);
}
