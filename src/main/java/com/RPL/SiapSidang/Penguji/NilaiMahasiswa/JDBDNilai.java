package com.RPL.SiapSidang.Penguji.NilaiMahasiswa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class JDBDNilai implements PengujiNilaiRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;

    //nilai 
    public List<KomponenNilai> getKomponen(){
        String sql = "SELECT DISTINCT(kn.deskripsi), ta.nilai_pu1 FROM Tugas_akhir ta JOIN Komponen_nilai kn ON ta.semester_akd = kn.semester AND ta.tahun_akd::VARCHAR = kn.tahun_ajaran;";
        return jdbcTemplate.query(sql, this::mapToRowKomponen);
    }

    private KomponenNilai mapToRowKomponen(ResultSet resultSet, int rowNum) throws SQLException   {
        return new KomponenNilai(
            resultSet.getString("deskripsi"),
            resultSet.getInt("nilai_pu1"));
    }
    
    //data mahasiswa
    public List<Mahasiswa> getDatMahasiswa() {
        String sql = "SELECT * FROM data_mahasiswa";
        return jdbcTemplate.query(sql, this::mapToRowMahasiswa);
    }

    private Mahasiswa mapToRowMahasiswa(ResultSet resultSet, int rowNum) throws SQLException{
        return new Mahasiswa(
            resultSet.getString("npm"), 
            resultSet.getString("nama"), 
            resultSet.getString("topik"));
    }

    //bobot
    public List<Bobot> getBobot(String deskripsi) {
        String sql = "SELECT bobotpenguji FROM Komponen_nilai WHERE deskripsi ILIKE ?";
        return jdbcTemplate.query(sql, this::mapToRowBobot, deskripsi);
    }

    private Bobot mapToRowBobot(ResultSet resultSet, int rowNum) throws SQLException{
        return new Bobot(resultSet.getInt("bobotpenguji"));
    }


    public void saveNilaiPenguji(double nilai, int bobot, String npm) {
          // Hitung nilai akhir berdasarkan bobot
          double nilaiAkhir = (nilai * bobot) / 100.0;
  
          // Query untuk memperbarui kolom nilai penguji
          String sql = "UPDATE Tugas_akhir SET nilai_pu1 WHERE npm = ?";
          
          // Update nilai di database
          jdbcTemplate.update(sql, nilaiAkhir, npm);
    }

 



    
}
