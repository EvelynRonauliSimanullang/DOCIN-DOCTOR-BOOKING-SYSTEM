package com.docin.service.impl;

import com.docin.dto.DokterLoginRequest;
import com.docin.dto.DokterLoginResponse;
import com.docin.dto.DokterRequest;
import com.docin.dto.DokterResponse;
import com.docin.entity.Dokter;
import com.docin.repository.DokterRepository;
import com.docin.security.JwtService;
import com.docin.service.DokterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.docin.dto.DokterKontakResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DokterServiceImpl implements DokterService {

    private final DokterRepository dokterRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public DokterResponse createDokter(DokterRequest request) {
        Dokter dokter = Dokter.builder()
                .fullName(request.getFullName())
                .spesialisasi(request.getSpecialization())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        dokterRepository.save(dokter);
        return mapToResponse(dokter);
    }

    @Override
    public DokterResponse updateDokter(Long id, DokterRequest request) {
        Dokter dokter = dokterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dokter not found"));
        dokter.setFullName(request.getFullName());
        dokter.setSpesialisasi(request.getSpecialization());
        dokter.setPhoneNumber(request.getPhoneNumber());
        dokter.setEmail(request.getEmail());
        dokter.setUsername(request.getUsername());
        dokter.setPassword(passwordEncoder.encode(request.getPassword()));
        dokterRepository.save(dokter);
        return mapToResponse(dokter);
    }

    @Override
    public DokterKontakResponse getKontakDokter(Long id) {
        Dokter dokter = dokterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dokter tidak ditemukan"));
        return DokterKontakResponse.builder()
                .fullName(dokter.getFullName())
                .email(dokter.getEmail())
                .phoneNumber(dokter.getPhoneNumber())
                .whatsapp(dokter.getPhoneNumber()) // gunakan nomor WA yang sama untuk dummy
                .build();
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
    public DokterResponse getProfile(String username) {
        Dokter dokter = dokterRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Dokter tidak ditemukan"));
        return mapToResponse(dokter);
    }

    @Override
    public DokterResponse getDokterById(Long id) {
        Dokter dokter = dokterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dokter not found"));
        return mapToResponse(dokter);
    }

    @Override
    public DokterLoginResponse login(DokterLoginRequest request) {
        Dokter dokter = dokterRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Username tidak ditemukan"));

        if (!passwordEncoder.matches(request.getPassword(), dokter.getPassword())) {
            throw new RuntimeException("Password salah");
        }

        String token = jwtService.generateToken(dokter);

        return DokterLoginResponse.builder()
                .username(dokter.getUsername())
                .token(token)
                .build();
    }

    private DokterResponse mapToResponse(Dokter dokter) {
        return DokterResponse.builder()
                .id(dokter.getId())
                .fullName(dokter.getFullName())
                .specialization(dokter.getSpesialisasi())
                .phoneNumber(dokter.getPhoneNumber())
                .email(dokter.getEmail())
                .username(dokter.getUsername())
                .build();
    }
}
