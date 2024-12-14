package com.RPL.SiapSidang.Pembimbing.CatatanSidang;

import java.util.List;

public interface PembimbingCatatanRepo {
    int getIdSidang(String npm);
    List<CatatanSidang> getCatatan(String npm);
    void saveCatatanSidang(String npm, String catatan);
    Mahasiswa getMahasiswaByNpm(String npm);
}
