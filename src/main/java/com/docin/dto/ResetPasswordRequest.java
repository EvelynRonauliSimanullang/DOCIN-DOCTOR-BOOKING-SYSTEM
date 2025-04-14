package com.docin.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {
    private String username;
    private String otp;
    private String newPassword;
}


