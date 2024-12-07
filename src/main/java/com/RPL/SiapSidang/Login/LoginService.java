package com.RPL.SiapSidang.Login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {

    @Autowired
    private MahasiswaRepo mahasiswaRepo;

    @Autowired
    private DosenRepo dosenRepo;

    public String login(String email, String password, HttpSession session) {
        // Cek login untuk Mahasiswa
        Optional<MahasiswaLogin> mahasiswaOpt = mahasiswaRepo.findByUsernameMahasiswa(email);
        if (mahasiswaOpt.isPresent() && mahasiswaOpt.get().getPassword().equals(password)) {
            MahasiswaLogin mahasiswa = mahasiswaOpt.get();
            session.setAttribute("id", mahasiswa.getNpm());
            session.setAttribute("name", mahasiswa.getNama());
            session.setAttribute("role", "mahasiswa");
            return "Login berhasil sebagai Mahasiswa";
        }

         // Cek login untuk Dosen
         Optional<DosenLogin> dosenOpt = dosenRepo.findByUsernameDosen(email);
         if (dosenOpt.isPresent() && dosenOpt.get().getPassword().equals(password)) {
             DosenLogin dosen = dosenOpt.get();
             session.setAttribute("id", dosen.getNik());
             session.setAttribute("name", dosen.getNama());
             session.setAttribute("role", dosen.getIsKoord() ? "koordinator" : "dosen");
             return "Login berhasil sebagai Dosen";
         }
 
         return "Email atau password salah";
    }
}