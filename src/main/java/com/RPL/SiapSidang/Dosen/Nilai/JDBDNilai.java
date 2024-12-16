package com.RPL.SiapSidang.Dosen.Nilai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class JDBDNilai implements NilaiRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;

    //nilai 
    public List<KomponenNilai> getKomponen(String npm, String peran) {
        String sql = "";
        if (peran.equals("PU1")) {
            sql = "SELECT nilai_ta.id_ta, nilai_ta.id_komp, kn.deskripsi, ROUND((nilaipenguji1 * 100.0) / bobotpenguji, 2) AS nilai FROM nilai_ta JOIN komponen_nilai kn ON kn.id_komp = nilai_ta.id_komp JOIN tugas_akhir ta ON ta.id_ta = nilai_ta.id_ta WHERE id_mahasiswa ILIKE ?";
        } else if (peran.equals("PU2")) {
            sql = "SELECT nilai_ta.id_ta, nilai_ta.id_komp, kn.deskripsi, ROUND((nilaipenguji2 * 100.0) / bobotpenguji, 2) AS nilai FROM nilai_ta JOIN komponen_nilai kn ON kn.id_komp = nilai_ta.id_komp JOIN tugas_akhir ta ON ta.id_ta = nilai_ta.id_ta WHERE id_mahasiswa ILIKE ?";
        } else if (peran.equals("PB1")) {
            sql = "SELECT nilai_ta.id_ta, nilai_ta.id_komp, kn.deskripsi, ROUND((nilaipembimbing1 * 100.0) / bobotpembimbing, 2) AS nilai FROM nilai_ta JOIN komponen_nilai kn ON kn.id_komp = nilai_ta.id_komp JOIN tugas_akhir ta ON ta.id_ta = nilai_ta.id_ta WHERE id_mahasiswa ILIKE ?";
        } else {
            throw new IllegalArgumentException("Invalid role: " + peran);
        }

        return jdbcTemplate.query(sql, this::mapToRowKomponen, npm);
    }

    private KomponenNilai mapToRowKomponen(ResultSet resultSet, int rowNum) throws SQLException   {
        return new KomponenNilai(
            resultSet.getInt("id_komp"),
            resultSet.getInt("id_ta"),
            resultSet.getString("deskripsi"),
            resultSet.getInt("nilai"));
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

    //ambil npm
    public Mahasiswa getNPM(String npm) {
        String sql = "SELECT * FROM data_mahasiswa WHERE npm = ?";
        
        List<Mahasiswa> mahasiswaList = jdbcTemplate.query(
            sql, 
            ps -> ps.setString(1, npm), 
            this::mapToRowMahasiswa 
        );
    
        // cek jika list kosong
        if (mahasiswaList.isEmpty()) {
            return null; // tidak mahasiswa dengan npm tertentu
        } else {
            return mahasiswaList.get(0); // Return hasil pertama
        }
    }
    public List<Bobot> getBobot(String deskripsi, String peran) {
        String sql = "";
        final String columnName;
    
        if (peran.equals("PU1") || peran.equals("PU2")) {
            columnName = "bobotpenguji";  
        } else if (peran.equals("PB1")) {
            columnName = "bobotpembimbing";  
        } else {
            throw new IllegalArgumentException("Invalid role: " + peran);
        }
    
        sql = "SELECT " + columnName + " FROM Komponen_nilai WHERE deskripsi ILIKE ?";        
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapToRowBobot(rs, columnName), deskripsi);
    }
    
    private Bobot mapToRowBobot(ResultSet resultSet, String columnName) throws SQLException {
        return new Bobot(resultSet.getInt(columnName)); 
    }
    
    //method save nilai ke tabel tugas akhir
    public void saveNilai(double nilaiAkhir, String npm, String peran) {
        String sql = "";

        if(peran.equals("PU1")){
            sql =  "UPDATE Tugas_akhir SET nilai_pu1 = ? WHERE id_mahasiswa = ?";
       }else if(peran.equals("PU2")){
            sql =  "UPDATE Tugas_akhir SET nilai_pu2 = ? WHERE id_mahasiswa = ?";
       }else if(peran.equals("PB1")){
            sql =  "UPDATE Tugas_akhir SET nilai_pb1 = ? WHERE id_mahasiswa = ?";
       }else{
           System.out.println("peran salah!");
       }
    
        jdbcTemplate.update(sql, nilaiAkhir, npm);
    }

    //ambil id ta
    public Integer getIdTa(String npm) {
        String sql = "SELECT id_ta FROM tugas_akhir WHERE id_mahasiswa = ?";
        
        List<Integer> idTaList = jdbcTemplate.query(
            sql,
            ps -> ps.setString(1, npm),
            (rs, rowNum) -> {
                try {
                    return rs.getInt("id_ta"); 
                } catch (SQLException e) {
                    System.err.println("Error retrieving id_ta: " + e.getMessage());
                    return null;
                }
            }
        );
        
        // Return hasil pertama 
        return idTaList.isEmpty() ? null : idTaList.get(0);
    }

    //ambil id komponen
    public List<Integer> getIdKompList(int idTa) {
        String sql = "SELECT id_komp FROM nilai_ta WHERE id_ta = ? ORDER BY id_komp";
        
        return jdbcTemplate.query(
            sql,
            ps -> ps.setInt(1, idTa),
            (rs, rowNum) -> rs.getInt("id_komp") 
        );
    }
    

    //method simpan nilai ke tabel nilai_ta  
    public void saveNilaiToNilai_TA(int id_ta, int id_komp, String peran, double nilaiAkhir) {
        String sql = "";

        if(peran.equals("PU1")){
            sql =  "UPDATE nilai_ta SET nilaipenguji1 = ? WHERE id_ta = ? AND id_komp = ?";
       }else if(peran.equals("PU2")){
            sql =  "UPDATE nilai_ta SET nilaipenguji2 = ? WHERE id_ta = ? AND id_komp = ?";
       }else if(peran.equals("PB1")){
            sql =  "UPDATE nilai_ta SET nilaipembimbing1 = ? WHERE id_ta = ? AND id_komp = ?";
       }else{
           System.out.println("peran salah!");
       }
    
        jdbcTemplate.update(sql, nilaiAkhir, id_ta, id_komp);
    }

}