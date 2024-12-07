package com.RPL.SiapSidang.Login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DosenLogin {
    private String nik;
    private String nama;
    private String kodeNama;
    private String email;
    private String password;
    private Boolean isKoord;
}
