package com.docin.controller;

import com.docin.dto.*;
import com.docin.service.DokterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dokters")
@RequiredArgsConstructor
public class DokterController {

    private final DokterService dokterService;

    @PostMapping("/register")
    public ResponseEntity<DokterResponse> register(@RequestBody @Valid DokterRequest request) {
        return ResponseEntity.ok(dokterService.createDokter(request));
    }

    @PostMapping("/login")
    public ResponseEntity<DokterLoginResponse> login(@RequestBody @Valid DokterLoginRequest request) {
        return ResponseEntity.ok(dokterService.login(request));
    }

    @GetMapping
    public ResponseEntity<List<DokterResponse>> getAllDokters() {
        return ResponseEntity.ok(dokterService.getAllDokter());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DokterResponse> getDokterById(@PathVariable Long id) {
        return ResponseEntity.ok(dokterService.getDokterById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DokterResponse> updateDokter(@PathVariable Long id,
                                                       @RequestBody @Valid DokterRequest request) {
        return ResponseEntity.ok(dokterService.updateDokter(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDokter(@PathVariable Long id) {
        dokterService.deleteDokter(id);
        return ResponseEntity.noContent().build();
    }
}