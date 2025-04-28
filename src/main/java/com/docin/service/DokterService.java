package com.docin.service;

import com.docin.dto.DokterRequest;
import com.docin.dto.DokterResponse;

import java.util.List;

public interface DokterService {
    DokterResponse createDokter(DokterRequest request);
    DokterResponse updateDokter(Long id, DokterRequest request);
    void deleteDokter(Long id);
    List<DokterResponse> getAllDokter();
    DokterResponse getDokterById(Long id);
}
