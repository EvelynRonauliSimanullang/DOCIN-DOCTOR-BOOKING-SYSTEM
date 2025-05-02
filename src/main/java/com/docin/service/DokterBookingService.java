package com.docin.service;

import com.docin.dto.DokterBookingResponse;

import java.util.List;

public interface DokterBookingService {
    List<DokterBookingResponse> getBookingsByDokter(Long dokterId);
}