package com.docin.service.impl;

import com.docin.dto.DokterRequest;
import com.docin.dto.DokterResponse;
import com.docin.entity.Dokter;
import com.docin.repository.DokterRepository;
import com.docin.service.DokterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DokterServiceImpl implements DokterService {

    private final DokterRepository dokterRepository;

    @Override
    public DokterResponse createDokter(DokterRequest request) {
        Dokter dokter = Dokter.builder()
                .name(request.getName())
                .specialization(request.getSpecialization())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .build();
        dokterRepository.save(dokter);
        return mapToResponse(dokter);
    }

    @Override
    public DokterResponse updateDokter(Long id, DokterRequest request) {
        Dokter dokter = dokterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dokter not found"));
        dokter.setName(request.getName());
        dokter.setSpecialization(request.getSpecialization());
        dokter.setPhoneNumber(request.getPhoneNumber());
        dokter.setEmail(request.getEmail());
        dokterRepository.save(dokter);
        return mapToResponse(dokter);
    }

    @Override
    public void deleteDokter(Long id) {
        Dokter dokter = dokterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dokter not found"));
        dokterRepository.delete(dokter);
    }

    @Override
    public List<DokterResponse> getAllDokter() {
        return dokterRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DokterResponse getDokterById(Long id) {
        Dokter dokter = dokterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dokter not found"));
        return mapToResponse(dokter);
    }

    private DokterResponse mapToResponse(Dokter dokter) {
        return DokterResponse.builder()
                .id(dokter.getId())
                .name(dokter.getName())
                .specialization(dokter.getSpecialization())
                .phoneNumber(dokter.getPhoneNumber())
                .email(dokter.getEmail())
                .build();
    }
}

