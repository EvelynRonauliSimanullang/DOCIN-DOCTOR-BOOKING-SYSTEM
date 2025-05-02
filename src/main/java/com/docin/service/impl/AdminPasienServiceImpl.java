package com.docin.service.impl;

import com.docin.dto.AdminPasienRequest;
import com.docin.dto.AdminPasienResponse;
import com.docin.entity.Pasien;
import com.docin.repository.PasienRepository;
import com.docin.service.AdminPasienService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminPasienServiceImpl implements AdminPasienService {

    private final PasienRepository pasienRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminPasienResponse createPasien(AdminPasienRequest request) {
        Pasien pasien = Pasien.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .otp(request.getOtp())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();
        pasienRepository.save(pasien);
        return mapToResponse(pasien);
    }

    @Override
    public AdminPasienResponse updatePasien(Long id, AdminPasienRequest request) {
        Pasien pasien = pasienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pasien not found"));
        pasien.setUsername(request.getUsername());
        pasien.setPassword(passwordEncoder.encode(request.getPassword()));
        pasien.setOtp(request.getOtp());
        pasien.setFullName(request.getFullName());
        pasien.setEmail(request.getEmail());
        pasien.setPhoneNumber(request.getPhoneNumber());
        pasienRepository.save(pasien);
        return mapToResponse(pasien);
    }

    @Override
    public void deletePasien(Long id) {
        pasienRepository.deleteById(id);
    }

    @Override
    public AdminPasienResponse getPasienById(Long id) {
        Pasien pasien = pasienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pasien not found"));
        return mapToResponse(pasien);
    }

    @Override
    public List<AdminPasienResponse> getAllPasien() {
        return pasienRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private AdminPasienResponse mapToResponse(Pasien pasien) {
        return AdminPasienResponse.builder()
                .id(pasien.getId())
                .username(pasien.getUsername())
                .fullName(pasien.getFullName())
                .email(pasien.getEmail())
                .phoneNumber(pasien.getPhoneNumber())
                .build();
    }
}