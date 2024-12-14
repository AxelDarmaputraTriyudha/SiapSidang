package com.RPL.SiapSidang.Penguji.NilaiMahasiswa;

import java.util.List;


public interface PengujiNilaiRepository {
    List<KomponenNilai> getKomponen(String npm, String peran);
    List<Mahasiswa> getDatMahasiswa();
    List<Bobot> getBobot(String deskripsi, String peran);
    void saveNilaiPenguji(double nilaiAkhir, String npm, String peran);
    void saveNilaiToNilai_TA(int id_ta, int id_komp, String peran, double nilaiAkhir);
}
