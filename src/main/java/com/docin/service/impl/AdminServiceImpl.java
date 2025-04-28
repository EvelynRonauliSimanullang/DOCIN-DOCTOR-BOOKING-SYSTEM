package com.docin.service.impl;

import com.docin.dto.AdminLoginRequest;
import com.docin.entity.Admin;
import com.docin.repository.AdminRepository;
import com.docin.security.JwtService;
import com.docin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String login(AdminLoginRequest request) {
        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        return jwtService.generateToken(admin);
    }
}
