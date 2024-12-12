package com.RPL.SiapSidang.Penguji.NilaiMahasiswa;

import java.util.List;


public interface PengujiNilaiRepository {
    List<KomponenNilai> getKomponen();
    List<Mahasiswa> getDatMahasiswa();
    List<Bobot> getBobot(String deskripsi);
    void saveNilaiPenguji(double nilaiAkhir, String npm);
}
