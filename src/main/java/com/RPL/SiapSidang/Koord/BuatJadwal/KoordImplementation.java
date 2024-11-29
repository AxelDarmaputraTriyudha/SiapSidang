package com.RPL.SiapSidang.Koord.BuatJadwal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KoordImplementation implements KoordRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Dosen> getAllDosen(){
        String sql = "SELECT * FROM dosen";
        return jdbcTemplate.query(sql, this::mapRowToDosen);
    }

    private Dosen mapRowToDosen(ResultSet resultSet, int rowNum) throws SQLException{
        return new Dosen(
            resultSet.getString("nik"),
            resultSet.getString("nama"),
            resultSet.getString("kode_nama"),
            resultSet.getString("email"),
            resultSet.getString("password")
        );
    }

    public List<Komponen> getAllKomponen(String semester, int tahun){
        String sql = "SELECT deskripsi, bobot_ketua_penguji,  bobot_anggota_penguji, bobot_pembimbing FROM lihat_komponen WHERE semester_akd = ? AND tahun_akd = ?";
        return jdbcTemplate.query(sql, this::mapRowToKomp, semester, tahun);
    }

    private Komponen mapRowToKomp(ResultSet resultSet, int rowNum) throws SQLException{
        return new Komponen(
            resultSet.getString("deskripsi"),
            resultSet.getInt("bobot_ketua_penguji"),
            resultSet.getInt("bobot_anggota_penguji"),
            resultSet.getInt("bobot_pembimbing")
        );
    }
}
