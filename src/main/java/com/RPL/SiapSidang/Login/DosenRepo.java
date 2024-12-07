package com.RPL.SiapSidang.Login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class DosenRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

      // Menyediakan metode untuk mencari mahasiswa berdasarkan email
    public Optional<DosenLogin> findByUsernameDosen (String email) {
        String sql = "SELECT * FROM Dosen WHERE email = ?";
        List<DosenLogin> results = jdbcTemplate.query(sql, this::mapRowToDosen, email);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }

    private DosenLogin mapRowToDosen(ResultSet resultSet, int rowNum) throws SQLException {
        return new DosenLogin(
            resultSet.getString("nik"),
            resultSet.getString("nama"),
            resultSet.getString("kodeNama"),
            resultSet.getString("email"),
            resultSet.getString("password"),
            resultSet.getBoolean("iskoord")
        );
    }
}
