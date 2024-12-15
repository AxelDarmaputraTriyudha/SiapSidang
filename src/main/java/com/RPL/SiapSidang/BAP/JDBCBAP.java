package com.RPL.SiapSidang.BAP;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCBAP {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<BAP> findData(String npm){
        String sql = "SELECT * FROM bap_view WHERE npm = ?";
        return jdbcTemplate.query(sql, this::mapRowToBAP, npm);
    }

    private BAP mapRowToBAP(ResultSet resultSet, int rowNum) throws SQLException{
        return new BAP(
            resultSet.getString("jenis"),
            resultSet.getString("nama"),
            resultSet.getString("judul"),
            resultSet.getString("npm"),
            resultSet.getString("semester_akd"),
            resultSet.getInt("tahun_akd"),
            resultSet.getString("waktu"),
            resultSet.getString("tanggal"),
            resultSet.getString("tempat"),
            resultSet.getString("nik"),
            resultSet.getString("nama_dosen"),
            resultSet.getString("peran"),
            resultSet.getDouble("nilai_pb1"),
            resultSet.getDouble("nilai_pu1"),
            resultSet.getDouble("nilai_pu2"),
            resultSet.getDouble("nilai_koord"),
            resultSet.getBoolean("status_bap"),
            resultSet.getInt("id_sidang")
        );
    }

    public void setStatusBAP(String npm){
        int id_sidang = findData(npm).get(0).getId_sidang();
        String sql = "UPDATE sidang_ta SET status_bap = TRUE WHERE id_sidang = ?";

        jdbcTemplate.update(sql, id_sidang);
    }
}
