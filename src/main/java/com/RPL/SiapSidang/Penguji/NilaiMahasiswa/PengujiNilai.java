package com.RPL.SiapSidang.Penguji.NilaiMahasiswa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PengujiNilai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private String npm;
    private String topikTugasAkhir;

    private int tataTulisLaporan;
    private int kelengkapanMateri;
    private int pencapaianTujuan;
    private int penguasaanMateri;
    private int presentasi;
}
