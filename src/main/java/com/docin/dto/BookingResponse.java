package com.docin.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private Long id;
    private String dokterName;
    private LocalDate bookingDate;
    private String pasienUsername;
}