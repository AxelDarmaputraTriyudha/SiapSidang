package com.RPL.SiapSidang.Koord.DetailJadwal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.RPL.SiapSidang.Koord.Home.DataSidang;

@Repository
public class JDBCDetailJadwalImplementation {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<DataSidang> findData(String npm){
        String sql = "SELECT * FROM data_sidang_view WHERE npm = ?";
        return jdbcTemplate.query(sql, this::mapRowToDataSidang, npm);
    }

    private DataSidang mapRowToDataSidang(ResultSet resultSet, int rowNum) throws SQLException{
        return new DataSidang(
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
            resultSet.getString("peran")
        );
    }
}
