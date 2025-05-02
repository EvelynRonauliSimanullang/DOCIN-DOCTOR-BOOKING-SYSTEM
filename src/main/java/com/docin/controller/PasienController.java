package com.docin.controller;

import com.docin.dto.*;
import com.docin.service.PasienService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.docin.service.DokterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PasienController {

    private final PasienService pasienService;
    private final DokterService dokterService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid PasienRequest request) {
        pasienService.register(request);
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request) {
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
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        return ResponseEntity.ok(pasienService.resetPassword(request));
    }

    @GetMapping("/dokters/{id}/kontak")
    public ResponseEntity<DokterKontakResponse> getKontakDokter(@PathVariable Long id) {
        return ResponseEntity.ok(dokterService.getKontakDokter(id)); // ✅ perbaikan
    }
}