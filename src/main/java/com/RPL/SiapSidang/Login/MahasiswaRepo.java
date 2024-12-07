package com.RPL.SiapSidang.Login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MahasiswaRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

      // Menyediakan metode untuk mencari mahasiswa berdasarkan email
    public Optional<MahasiswaLogin> findByUsernameMahasiswa (String email) {
        String sql = "SELECT * FROM Mahasiswa WHERE email = ?";
        List<MahasiswaLogin> results = jdbcTemplate.query(sql, this::mapRowToMahasiswa, email);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }

    private MahasiswaLogin mapRowToMahasiswa(ResultSet resultSet, int rowNum) throws SQLException {
        return new MahasiswaLogin(
            resultSet.getString("npm"),
            resultSet.getString("nama"),
            resultSet.getString("email"),
            resultSet.getString("password")
        );
    }

}
