package com.RPL.SiapSidang.Pembimbing.CatatanSidang;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCCatatan {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> findDataByIdTugasAkhir(int idTugasAkhir) {
        String sql = """
            SELECT m.nama, m.npm, ta.judul 
            FROM mahasiswa m 
            JOIN tugas_akhir ta ON m.npm = ta.npm 
            WHERE ta.id_tugas_akhir = ?
        """;
        return jdbcTemplate.queryForMap(sql, idTugasAkhir);
    }

    public void saveCatatan(int idTugasAkhir, String catatan) {
        String sql = "INSERT INTO sidang_ta (id_tugas_akhir, catatan) VALUES (?, ?) " +
                     "ON DUPLICATE KEY UPDATE catatan = ?";
        jdbcTemplate.update(sql, idTugasAkhir, catatan, catatan);
    }
}
