package com.docin.dto;

import lombok.Data;
@Data
public class AdminPasienRequest {
    private String username;
    private String password;
    private String otp;
    private String fullName;
    private String email;
    private String phoneNumber;
}

