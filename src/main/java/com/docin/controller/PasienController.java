package com.docin.controller;

import com.docin.dto.*;
import com.docin.service.PasienService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PasienController {

    private final PasienService pasienService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody PasienRequest request) {
        pasienService.register(request);
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(pasienService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String username) {
        pasienService.logout(username);
        return ResponseEntity.ok("Logout successful");
    }

    @PostMapping("/generate-otp")
    public ResponseEntity<String> generateOtp(@RequestParam String username) {
        return ResponseEntity.ok("OTP: " + pasienService.generateOtp(username));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        return ResponseEntity.ok(pasienService.resetPassword(request));
    }
}
