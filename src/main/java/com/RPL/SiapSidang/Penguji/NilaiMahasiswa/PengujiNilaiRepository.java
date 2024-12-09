package com.RPL.SiapSidang.Penguji.NilaiMahasiswa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PengujiNilaiRepository extends JpaRepository<PengujiNilai, Long> {
}
