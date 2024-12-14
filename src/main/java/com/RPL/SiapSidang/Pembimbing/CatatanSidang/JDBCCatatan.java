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

    // Mengambil semua data mahasiswa dari view
    @Override
    public List<Mahasiswa> getDataMahasiswa() {
        String sql = "SELECT npm, nama, judul FROM view_sidang_mahasiswa";
        return jdbcTemplate.query(sql, this::mapToRowMahasiswa);
    }

    // Mapping Mahasiswa dari ResultSet
    private Mahasiswa mapToRowMahasiswa(ResultSet resultSet, int rowNum) throws SQLException {
        return new Mahasiswa(
            resultSet.getString("npm"),
            resultSet.getString("nama"),
            resultSet.getString("judul")
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
        String sql = "INSERT INTO sidang_ta (id_ta, catatan_sidang) SELECT ta.id_ta, ? FROM tugas_akhir ta JOIN mahasiswa m ON ta id_mahasiswa = m.npm WHERE m.npm = ?";

        jdbcTemplate.update(sql, npm, catatan);
    }

    // Mengambil data mahasiswa berdasarkan NPM dari view
    public Mahasiswa getMahasiswaByNpm(String npm) {
        String sql = "SELECT * FROM data_mahasiswa WHERE npm = ?";
        
        // Query the database and map the result to a Mahasiswa object
        List<Mahasiswa> mahasiswaList = jdbcTemplate.query(
            sql, 
            ps -> ps.setString(1, npm), 
            this::mapToRowMahasiswa // Map setiap row ke objek mahasiswa
        );
    
        // cek jika list kosong
        if (mahasiswaList.isEmpty()) {
            return null; // tidak mahasiswa dengan npm tertentu
        } else {
            return mahasiswaList.get(0); // Return hasil pertama
        }
    }
    
}
