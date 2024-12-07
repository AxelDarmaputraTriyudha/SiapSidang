package com.RPL.SiapSidang.Login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MahasiswaLogin {
    private String npm;
    private String nama;
    private String email;
    private String password;
}
