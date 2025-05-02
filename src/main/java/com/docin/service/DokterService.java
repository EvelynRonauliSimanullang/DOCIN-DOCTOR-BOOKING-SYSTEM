package com.docin.service;

import com.docin.dto.DokterRequest;
import com.docin.dto.DokterResponse;
import com.docin.dto.DokterLoginRequest;
import com.docin.dto.DokterLoginResponse;
import com.docin.dto.DokterKontakResponse;

import java.util.List;

public interface DokterService {
    DokterResponse createDokter(DokterRequest request);
    DokterResponse updateDokter(Long id, DokterRequest request);
    void deleteDokter(Long id);
    List<DokterResponse> getAllDokter();
    DokterResponse getProfile(String username);
    DokterResponse getDokterById(Long id);
    DokterLoginResponse login(DokterLoginRequest request);
    DokterKontakResponse getKontakDokter(Long id); // ✅ ditambahkan
}

