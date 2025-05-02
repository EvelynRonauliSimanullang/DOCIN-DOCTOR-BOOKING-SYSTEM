package com.docin.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DokterProfileResponse {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String spesialisasi;
    private String phoneNumber;
}
