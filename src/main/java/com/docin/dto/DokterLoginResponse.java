package com.docin.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DokterLoginResponse {
    private String username;
    private String token;
}
