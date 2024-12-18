package com.RPL.SiapSidang.Dosen.Home;

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
public class JDBCDosenRepositoryImplementation {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<DataSidang> findAll(String filter,
                                    LocalDate tgl_awal,
                                    LocalDate tgl_akhir,
                                    String semester,
                                    int tahun,
                                    String nik){

        String sql = "SELECT * FROM data_sidang_view WHERE nik = ?";
        
        // kumpulan filter yang digunakan
        List<Object> filterList = new ArrayList<>();
        filterList.add(nik);

        // cari filter apa aja yang dimasukkin
        // kalo filternya ada, tambahin ke query SQL nya
        if(filter.length() > 0){
            sql += " AND nama ILIKE ?";
            filterList.add("%" + filter + "%");
        }
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
}
