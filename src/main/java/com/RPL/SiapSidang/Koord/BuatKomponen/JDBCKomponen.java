package com.RPL.SiapSidang.Koord.BuatKomponen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCKomponen {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addKomponen(Komponen komponen){
        String sql = "INSERT INTO komponen_nilai (deskripsi, bobotpenguji, bobotpembimbing, semester, tahun_ajaran) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, 
        komponen.getDeskripsi(),
        komponen.getBobotpenguji(),
        komponen.getBobotpembimbing(),
        komponen.getSemester(),
        komponen.getTahun_ajaran());
    }
}
