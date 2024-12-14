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
    public List<KomponenNilai> getKomponen(String npm, String peran){
        String sql ="";
        if(peran.equals("PU1")){
             sql = "SELECT deskripsi, nilai_pu1 FROM Tugas_akhir ta JOIN Komponen_nilai kn ON ta.semester_akd = kn.semester AND ta.tahun_akd::VARCHAR = kn.tahun_ajaran WHERE id_mahasiswa ILIKE ?";
        }else if(peran.equals("PU2")){
             sql = "SELECT deskripsi, nilai_pu2 FROM Tugas_akhir ta JOIN Komponen_nilai kn ON ta.semester_akd = kn.semester AND ta.tahun_akd::VARCHAR = kn.tahun_ajaran WHERE id_mahasiswa ILIKE ?";
        }else if(peran.equals("PB1")){
             sql = "SELECT deskripsi, nilai_pb1 FROM Tugas_akhir ta JOIN Komponen_nilai kn ON ta.semester_akd = kn.semester AND ta.tahun_akd::VARCHAR = kn.tahun_ajaran WHERE id_mahasiswa ILIKE ?";
        }else{
            System.out.println("peran salah!");
        }
        
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapToRowKomponen(rs, peran.equals("PB1") ? "nilai_pb1" : "nilai_pu1"), npm);
    }

    private KomponenNilai mapToRowKomponen(ResultSet resultSet, String columnName) throws SQLException   {
        return new KomponenNilai(
            resultSet.getString("deskripsi"),
            resultSet.getInt(columnName));
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
            resultSet.getString("judul"));
    }

    public Mahasiswa getNPM(String npm) {
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
    

    //bobot
    public List<Bobot> getBobot(String deskripsi) {
        String sql = "SELECT bobotpenguji FROM Komponen_nilai WHERE deskripsi ILIKE ?";
        return jdbcTemplate.query(sql, this::mapToRowBobot, deskripsi);
    }

    private Bobot mapToRowBobot(ResultSet resultSet, int rowNum) throws SQLException{
        return new Bobot(resultSet.getInt("bobotpenguji"));
    }


    public void saveNilaiPenguji(double nilaiAkhir, String npm) {
        String sql = "UPDATE Tugas_akhir SET nilai_pu1 = ? WHERE id_mahasiswa = ?";

        // Log the query and parameters
        System.out.println("Executing SQL: " + sql + " with nilaiAkhir=" + nilaiAkhir + " and npm=" + npm);
    
        jdbcTemplate.update(sql, nilaiAkhir, npm);
    }

 



    
}
