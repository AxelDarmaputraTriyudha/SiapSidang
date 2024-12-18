package com.RPL.SiapSidang.Mahasiswa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.RPL.SiapSidang.Koord.Home.DataSidang;

@Repository
public class JDBCMahasiswaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<DataSidang> findAll(LocalDate tgl_awal,
                                    LocalDate tgl_akhir,
                                    String semester,
                                    int tahun,
                                    String npm){

        String sql = "SELECT * FROM data_sidang_view WHERE npm = ? AND peran = 'PB1'";
        
        // kumpulan filter yang digunakan
        List<Object> filterList = new ArrayList<>();
        filterList.add(npm);

        // cari filter apa aja yang dimasukkin
        // kalo filternya ada, tambahin ke query SQL nya
        if(tgl_awal != null){
            sql += " AND tanggal >= ?";
            filterList.add(tgl_awal);
        }
        if(tgl_akhir != null){
            sql += " AND tanggal <= ?";
            filterList.add(tgl_akhir);
        }
        if(semester.length() > 0){
            sql += " AND semester_akd = ?";
            filterList.add(semester);
        }
        if(tahun > 0){
            sql += " AND tahun_akd = ?";
            filterList.add(tahun);
        }

        return jdbcTemplate.query(sql, this::mapRowToDataSidang, filterList.toArray());
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

    public List<DataSidang> findDetail(String npm){
        String sql = "SELECT * FROM data_sidang_view WHERE npm = ?";
        return jdbcTemplate.query(sql, this::mapRowToDataSidang, npm);
    }

    public List<NilaiMahasiswa> findNilai(String npm){
        String sql = "SELECT * FROM tugas_akhir WHERE id_mahasiswa = ?";
        return jdbcTemplate.query(sql, this::mapRowToNilaiMahasiswa, npm);
    }

    private NilaiMahasiswa mapRowToNilaiMahasiswa(ResultSet resultSet, int rowNum) throws SQLException {
        return new NilaiMahasiswa(
            resultSet.getInt("id_ta"),
            resultSet.getString("id_mahasiswa"), 
            resultSet.getString("judul"),
            resultSet.getDouble("nilai_pb1"),
            resultSet.getDouble("nilai_pu1"),
            resultSet.getDouble("nilai_pu2"),
            resultSet.getDouble("nilai_koord"), 
            resultSet.getDouble("nilai_akhir")
        );
    }

    public void updateNilaiAkhir(double nilaiAkhir, int id_ta){
        String sql = "UPDATE tugas_akhir SET nilai_akhir = ? WHERE id_ta = ?";
        jdbcTemplate.update(sql, nilaiAkhir, id_ta);
    }

    public void updateAngkaAkhir(String angkaAkhir, int id_ta){
        String sql = "UPDATE tugas_akhir SET angka_akhir = ? WHERE id_ta = ?";
        jdbcTemplate.update(sql, angkaAkhir, id_ta);
    }
}