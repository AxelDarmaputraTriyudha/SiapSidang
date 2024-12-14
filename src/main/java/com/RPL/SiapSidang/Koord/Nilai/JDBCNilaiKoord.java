package com.RPL.SiapSidang.Koord.Nilai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    private NilaiKoord maptoNilaiKoord(ResultSet resultSet, int rowNum) throws SQLException {
        return new NilaiKoord(resultSet.getDouble("nilai_koord"));
    }    

    public NilaiKoord getNilai(String npm) {
        String sql = "SELECT nilai_koord FROM tugas_akhir WHERE id_mahasiswa = ?";
        List<NilaiKoord> list = jdbcTemplate.query(sql, this::maptoNilaiKoord, npm);
        if (list.isEmpty()) {
            return null; // Kembalikan null jika tidak ada data
        }
        return list.get(0);
    }    
}
