package com.RPL.SiapSidang.Login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String nomor;
    private String nama;
    private String kode_nama;
    private String email;
    private String password;
    private Boolean isKoord;
    private boolean isMhs;
    private boolean isDosen;
}
