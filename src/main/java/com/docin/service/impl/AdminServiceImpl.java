package com.docin.service.impl;

import com.docin.dto.AdminLoginRequest;
import com.docin.dto.AdminResponse;
import com.docin.entity.Admin;
import com.docin.repository.AdminRepository;
import com.docin.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.docin.service.AdminService;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AdminResponse login(AdminLoginRequest request) {
        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Username tidak ditemukan"));

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Password salah");
        }

        String token = jwtService.generateToken(admin);

        return AdminResponse.builder()
                .username(admin.getUsername())
                .token(token)
                .build();
    }
}
