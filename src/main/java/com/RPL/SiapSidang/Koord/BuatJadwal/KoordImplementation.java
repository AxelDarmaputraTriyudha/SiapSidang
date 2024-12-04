package com.RPL.SiapSidang.Koord.BuatJadwal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KoordImplementation implements KoordRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;
    private static String[] listHari = {"Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};

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

    public List<Komponen> getAllKomponen(String semester, String tahun){
        String sql = "SELECT deskripsi, bobotpenguji,  bobotpembimbing FROM komponen_nilai WHERE semester ILIKE ? AND tahun_ajaran ILIKE ?";
        return jdbcTemplate.query(sql, this::mapRowToKomp, semester, tahun);
    }

    private Komponen mapRowToKomp(ResultSet resultSet, int rowNum) throws SQLException{
        return new Komponen(
            resultSet.getString("deskripsi"),
            resultSet.getInt("bobotpenguji"),
            resultSet.getInt("bobotpembimbing")
        );
    }

    public TA getTA (String npm){
        String sql = "SELECT id_ta, npm, nama, judul FROM tugas_akhir JOIN mahasiswa ON mahasiswa.npm = tugas_akhir.id_mahasiswa WHERE id_mahasiswa = ?";

        List<TA> lisTA = jdbcTemplate.query(sql, this::mapRowToTA, npm);
        return lisTA.get(0);
    }

    private TA mapRowToTA(ResultSet resultSet, int rowNum) throws SQLException{
        return new TA(
            resultSet.getInt("id_ta"),
            resultSet.getString("npm"), resultSet.getString("nama"), resultSet.getString("judul"));
    }

    public void setJadwal(Sidang penguji1, Sidang penguji2, Sidang pembimbing1, Sidang pembimbing2){
        String sql = "SELECT * FROM last_id_sidang;";
        int idTA = jdbcTemplate.queryForObject(sql, Integer.class);
        idTA += 1;
        
        DayOfWeek dayOfWeek = penguji1.getTanggal().getDayOfWeek();
        int dayIndex = dayOfWeek.getValue();
        String hari = listHari[dayIndex % 7];

        sql = "INSERT INTO sidang_ta (id_sidang, nik, id_ta, peran, hari, tanggal, waktu, tempat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
            sql, 
            idTA,
            penguji1.getNik(),
            penguji1.getId_ta(),
            penguji1.getPeran(),
            hari,
            penguji1.getTanggal(),
            penguji1.getWaktu(),
            penguji1.getTempat());
        
        sql = "INSERT INTO sidang_ta (id_sidang, nik, id_ta, peran, hari, tanggal, waktu, tempat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
            sql, 
            idTA,
            penguji2.getNik(),
            penguji2.getId_ta(),
            penguji2.getPeran(),
            hari,
            penguji2.getTanggal(),
            penguji2.getWaktu(),
            penguji2.getTempat());

        sql = "INSERT INTO sidang_ta (id_sidang, nik, id_ta, peran, hari, tanggal, waktu, tempat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
            sql, 
            idTA,
            pembimbing1.getNik(),
            pembimbing1.getId_ta(),
            pembimbing1.getPeran(),
            hari,
            pembimbing1.getTanggal(),
            pembimbing1.getWaktu(),
            pembimbing1.getTempat());

        if(pembimbing2 != null){
            sql = "INSERT INTO sidang_ta (id_sidang, nik, id_ta, peran, hari, tanggal, waktu, tempat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(
            sql, 
            idTA,
            pembimbing2.getNik(),
            pembimbing2.getId_ta(),
            pembimbing2.getPeran(),
            hari,
            pembimbing2.getTanggal(),
            pembimbing2.getWaktu(),
            pembimbing2.getTempat());
        }
        
    }
}
