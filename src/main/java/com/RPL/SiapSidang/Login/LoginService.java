/*
 * 
 */


package com.RPL.SiapSidang.Login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    @Autowired
    private UserRepo userRepo;

    public User login(String email, String password) {

        Optional<User> mahasiswaOpt = userRepo.findByUsernameMahasiswa(email);
        Optional<User> dosenOpt = userRepo.findByUsernameDosen(email);

        if(mahasiswaOpt.isPresent() && mahasiswaOpt.get().getPassword().equals(password)){ // login jika mahasiswa
            User mhs = mahasiswaOpt.get();
            return mhs;
        }
        else if(dosenOpt.isPresent() && dosenOpt.get().getPassword().equals(password)){
            if(dosenOpt.get().getIsKoord()){
                User dosenKoor = dosenOpt.get();
                return dosenKoor;
            }
            else{
                User dosen = dosenOpt.get();
                return dosen; 
            }
        }
        else{
            return null;
        }
    }
}