package com.docin.service.impl;

import com.docin.dto.DokterProfileResponse;
import com.docin.entity.Dokter;
import com.docin.repository.DokterRepository;
import com.docin.service.DokterProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DokterProfileServiceImpl implements DokterProfileService {

    private final DokterRepository dokterRepository;

    @Override
    public DokterProfileResponse getProfile(Long dokterId) {
        Dokter dokter = dokterRepository.findById(dokterId)
                .orElseThrow(() -> new RuntimeException("Dokter tidak ditemukan"));
        return DokterProfileResponse.builder()
                .id(dokter.getId())
                .username(dokter.getUsername())
                .fullName(dokter.getFullName())
                .email(dokter.getEmail())
                .spesialisasi(dokter.getSpesialisasi())
                .phoneNumber(dokter.getPhoneNumber())
                .build();
    }
}