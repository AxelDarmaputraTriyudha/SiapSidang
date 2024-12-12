package com.RPL.SiapSidang.Pembimbing.CatatanSidang;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatatanSidang {
    private String npm;
    private String nama;
    private String judul;
    private String catatan;
}
