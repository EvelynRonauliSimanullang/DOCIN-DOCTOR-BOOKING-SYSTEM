package com.docin.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DokterResponse {
    private Long id;
    private String fullName;
    private String specialization;
    private String phoneNumber;
    private String email;
    private String username;
}
