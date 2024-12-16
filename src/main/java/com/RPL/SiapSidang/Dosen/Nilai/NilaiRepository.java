package com.RPL.SiapSidang.Dosen.Nilai;

import java.util.List;


public interface NilaiRepository {
    List<KomponenNilai> getKomponen(String npm, String peran);
    List<Mahasiswa> getDatMahasiswa();
    List<Bobot> getBobot(String deskripsi, String peran);
    void saveNilai(double nilaiAkhir, String npm, String peran);
    void saveNilaiToNilai_TA(int id_ta, int id_komp, String peran, double nilaiAkhir);
}