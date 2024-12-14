package com.RPL.SiapSidang.Pembimbing.CatatanSidang;

import java.util.List;
import java.util.Map;

public interface PembimbingCatatanRepo {
    List<Mahasiswa> getDataMahasiswa();
    List<CatatanSidang> getCatatan(String npm);
    void saveCatatanSidang(String npm, String catatan);
}
