package com.RPL.SiapSidang.Login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    

    public Optional<User> findByUsernameMahasiswa (String email) {
        String sql = "SELECT * FROM Mahasiswa WHERE email = ?";
        List<User> results = jdbcTemplate.query(sql, this::mapRowToMahasiswa, email);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }

    private User mapRowToMahasiswa(ResultSet resultSet, int rowNum) throws SQLException {
        return new User(
            resultSet.getString("npm"),
            resultSet.getString("nama"),
            null, 
            resultSet.getString("email"), 
            resultSet.getString("password"),
            false,
            true,
            false
            );
    }

    // Menyediakan metode untuk mencari Dosen berdasarkan email
    public Optional<User> findByUsernameDosen (String email) {
        String sql = "SELECT * FROM Dosen WHERE email = ?";
        List<User> results = jdbcTemplate.query(sql, this::mapRowToDosen, email);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }

    private User mapRowToDosen(ResultSet resultSet, int rowNum) throws SQLException {
        return new User(
            resultSet.getString("nik"),
            resultSet.getString("nama"),
            resultSet.getString("kode_nama"),
            resultSet.getString("email"),
            resultSet.getString("password"),
            resultSet.getBoolean("iskoord"),
            false,
            true);
    }
}
