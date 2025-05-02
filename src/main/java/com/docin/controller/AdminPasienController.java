package com.docin.controller;

import com.docin.dto.BaseResponse;
import com.docin.dto.AdminPasienRequest;
import com.docin.dto.AdminPasienResponse;
import com.docin.service.AdminPasienService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins/pasien")
@RequiredArgsConstructor
public class AdminPasienController {

    private final AdminPasienService pasienService;

    @PostMapping
    public ResponseEntity<BaseResponse<AdminPasienResponse>> create(@RequestBody @Valid AdminPasienRequest request) {
        return ResponseEntity.ok(
                BaseResponse.<AdminPasienResponse>builder()
                        .success(true)
                        .message("Pasien created")
                        .data(pasienService.createPasien(request))
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<AdminPasienResponse>> update(@PathVariable Long id, @RequestBody @Valid AdminPasienRequest request) {
        return ResponseEntity.ok(
                BaseResponse.<AdminPasienResponse>builder()
                        .success(true)
                        .message("Pasien updated")
                        .data(pasienService.updatePasien(id, request))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id) {
        pasienService.deletePasien(id);
        return ResponseEntity.ok(
                BaseResponse.<Void>builder()
                        .success(true)
                        .message("Pasien deleted")
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<AdminPasienResponse>>> getAll() {
        return ResponseEntity.ok(
                BaseResponse.<List<AdminPasienResponse>>builder()
                        .success(true)
                        .message("List of pasien")
                        .data(pasienService.getAllPasien())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<AdminPasienResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                BaseResponse.<AdminPasienResponse>builder()
                        .success(true)
                        .message("Pasien found")
                        .data(pasienService.getPasienById(id))
                        .build()
        );
    }
}