package com.RPL.SiapSidang.Koord.BuatJadwal;

import java.util.List;

public interface KoordRepository {
    List<Dosen> getAllDosen();
    List<Komponen> getAllKomponen(String semester, String tahun);
    TA getTA(String npm);
    void setJadwal(Sidang penguji1, Sidang penguji2, Sidang pembimbing1, Sidang pembimbing2);
}
