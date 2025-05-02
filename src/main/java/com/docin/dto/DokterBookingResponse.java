package com.docin.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class DokterBookingResponse {
    private Long bookingId;
    private String pasienUsername;
    private LocalDate tanggal;
    private LocalTime jam;
    private String status;
}