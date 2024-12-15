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
    public List<KomponenNilai> getKomponen(String npm, String peran) {
        String sql = "";
        if (peran.equals("PU1")) {
            sql = "SELECT ta.id_ta, id_komp, deskripsi, nilai_pu1 FROM Tugas_akhir ta JOIN Komponen_nilai kn ON ta.semester_akd = kn.semester AND ta.tahun_akd::VARCHAR = kn.tahun_ajaran WHERE id_mahasiswa ILIKE ?";
        } else if (peran.equals("PU2")) {
            sql = "SELECT ta.id_ta, id_komp, deskripsi, nilai_pu2 FROM Tugas_akhir ta JOIN Komponen_nilai kn ON ta.semester_akd = kn.semester AND ta.tahun_akd::VARCHAR = kn.tahun_ajaran WHERE id_mahasiswa ILIKE ?";
        } else if (peran.equals("PB1")) {
            sql = "SELECT ta.id_ta, id_komp, deskripsi, nilai_pb1 FROM Tugas_akhir ta JOIN Komponen_nilai kn ON ta.semester_akd = kn.semester AND ta.tahun_akd::VARCHAR = kn.tahun_ajaran WHERE id_mahasiswa ILIKE ?";
        } else {
            throw new IllegalArgumentException("Invalid role: " + peran);
        }
        
        return jdbcTemplate.query(
            sql,
            ps -> ps.setString(1, npm), // Set the parameter for the query
            (rs, rowNum) -> mapToRowKomponen(rs, peran.equals("PB1") ? "nilai_pb1" : (peran.equals("PU2") ? "nilai_pu2" : "nilai_pu1"))
        );
    }

    private KomponenNilai mapToRowKomponen(ResultSet resultSet, String columnName) throws SQLException   {
        return new KomponenNilai(
            resultSet.getInt("id_komp"),
            resultSet.getInt("id_ta"),
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
        
        // Declare and initialize the final variable directly
        final String columnName;
    
        if (peran.equals("PU1") || peran.equals("PU2")) {
            columnName = "bobotpenguji";  
        } else if (peran.equals("PB1")) {
            columnName = "bobotpembimbing";  
        } else {
            throw new IllegalArgumentException("Invalid role: " + peran); // Handle invalid role
        }
    
        sql = "SELECT " + columnName + " FROM Komponen_nilai WHERE deskripsi ILIKE ?";
        System.out.println("Executing SQL: " + sql + " with deskripsi=" + deskripsi);
        
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapToRowBobot(rs, columnName), deskripsi);
    }
    
    
    


    private Bobot mapToRowBobot(ResultSet resultSet, String columnName) throws SQLException {
        return new Bobot(resultSet.getInt(columnName)); // Use the dynamic column name
    }
    

    //method save nilai ke tabel tugas akhir
    public void saveNilaiPenguji(double nilaiAkhir, String npm, String peran) {
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

        System.out.println("Executing SQL: " + sql + " with nilaiAkhir=" + nilaiAkhir + " and npm=" + npm);
    
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
                    System.err.println("Error retrieving id_ta: " + e.getMessage()); // Log any SQL exceptions
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

        // debug
        System.out.println("Executing SQL: " + sql + " with nilaiAkhir=" + nilaiAkhir + " id_ta=" + id_ta + "id_komp=" + id_komp);
    
        jdbcTemplate.update(sql, nilaiAkhir, id_ta, id_komp);
    }




    
}