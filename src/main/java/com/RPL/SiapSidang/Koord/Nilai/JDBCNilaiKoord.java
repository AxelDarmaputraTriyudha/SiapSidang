package com.RPL.SiapSidang.Koord.Nilai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCNilaiKoord {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void tambahNilai(String npm, Double nilai){
        String sql = "UPDATE tugas_akhir SET nilai_koord = ? WHERE id_mahasiswa = ?";
        jdbcTemplate.update(sql, nilai, npm);
    }
}
