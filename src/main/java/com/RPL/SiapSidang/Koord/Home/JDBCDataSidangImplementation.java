package com.RPL.SiapSidang.Koord.Home;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCDataSidangImplementation {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<DataSidang> findAll(){
        String sql = "SELECT * FROM data_sidang_view WHERE peran = 'Koordinator'";
        return jdbcTemplate.query(sql, this::mapRowToDataSidang);
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
    
    public List<DataSidang> findWithFilter(String filter){
        String sql = "SELECT * FROM data_sidang_view WHERE nama ILIKE ? AND peran = 'Koordinator'";
        return jdbcTemplate.query(sql, this::mapRowToDataSidang, "%"+filter+"%");
    }
}