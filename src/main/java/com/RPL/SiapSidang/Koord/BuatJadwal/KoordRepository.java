package com.RPL.SiapSidang.Koord.BuatJadwal;

import java.util.List;

public interface KoordRepository {
    List<Dosen> getAllDosen();
    List<Komponen> getAllKomponen(String semester, int tahun);
}
