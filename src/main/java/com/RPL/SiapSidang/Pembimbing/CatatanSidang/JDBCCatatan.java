package com.RPL.SiapSidang.Pembimbing.CatatanSidang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCCatatan implements PembimbingCatatanRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int getIdSidang(String npm) {
        String sql = "SELECT npm, nama, judul, id_ta FROM view_sidang_mahasiswa WHERE npm = ?";
        List<Mahasiswa> list = jdbcTemplate.query(sql, this::mapToRowMahasiswa, npm);
        if (list.isEmpty()) {
            throw new RuntimeException("Data mahasiswa dengan NPM " + npm + " tidak ditemukan.");
        }
        return list.get(0).getId_ta();
    }

    // Mapping Mahasiswa dari ResultSet
    private Mahasiswa mapToRowMahasiswa(ResultSet resultSet, int rowNum) throws SQLException {
        return new Mahasiswa(
            resultSet.getString("npm"),
            resultSet.getString("nama"),
            resultSet.getString("judul"),
            resultSet.getInt("id_ta")
        );
    }

    // Mengambil catatan sidang berdasarkan NPM dari view
    @Override
    public List<CatatanSidang> getCatatan(String npm) {
        String sql = "SELECT catatan_sidang FROM view_sidang_mahasiswa WHERE npm = ?";
        return jdbcTemplate.query(sql, this::mapToRowCatatanSidang, npm);
    }

    // Mapping CatatanSidang dari ResultSet
    private CatatanSidang mapToRowCatatanSidang(ResultSet resultSet, int rowNum) throws SQLException {
        return new CatatanSidang(
            resultSet.getString("catatan_sidang")
        );
    }

    // Menyimpan atau memperbarui catatan sidang ke tabel asli
    @Override
    public void saveCatatanSidang(String npm, String catatan) {
        int id_ta = this.getIdSidang(npm);
        String sql = "UPDATE sidang_ta SET catatan_sidang = ? WHERE id_ta = ?";
        jdbcTemplate.update(sql, catatan, id_ta);
    }

    // Mengambil data mahasiswa berdasarkan NPM dari view
    public Mahasiswa getMahasiswaByNpm(String npm) {
        String sql = "SELECT * FROM data_mahasiswa WHERE npm = ?";
        List<Mahasiswa> list = jdbcTemplate.query(sql, this::mapToRowMahasiswa, npm);
        if (list.isEmpty()) {
            throw new RuntimeException("Mahasiswa dengan NPM " + npm + " tidak ditemukan.");
        }
        return list.get(0);
    }    
    
}
