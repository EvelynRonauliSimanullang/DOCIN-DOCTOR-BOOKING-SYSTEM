package com.docin.service.impl;

import com.docin.dto.LoginRequest;
import com.docin.dto.PasienRequest;
import com.docin.dto.ResetPasswordRequest;
import com.docin.entity.Pasien;
import com.docin.repository.PasienRepository;
import com.docin.security.JwtService;
import com.docin.service.PasienService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class PasienServiceImpl implements PasienService {

    private final PasienRepository pasienRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void register(PasienRequest request) {
        if (pasienRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Pasien pasien = Pasien.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        pasienRepository.save(pasien);
    }

    @Override
    public String login(LoginRequest request) {
        Pasien pasien = pasienRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), pasien.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        // Generate JWT Token
        return jwtService.generateToken(pasien);
    }

    @Override
    public void logout(String username) {
        // Jika token disimpan di server, bisa blacklist token di sini
        System.out.println("User logged out: " + username);
    }

    @Override
    public String generateOtp(String username) {
        Pasien pasien = pasienRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String otp = String.format("%06d", new Random().nextInt(1000000));
        pasien.setOtp(otp);
        pasienRepository.save(pasien);

        return otp;
    }

    @Override
    public String resetPassword(ResetPasswordRequest request) {
        Pasien pasien = pasienRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!request.getOtp().equals(pasien.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        pasien.setPassword(passwordEncoder.encode(request.getNewPassword()));
        pasien.setOtp(null);
        pasienRepository.save(pasien);

        return "Password reset successful";
    }
}