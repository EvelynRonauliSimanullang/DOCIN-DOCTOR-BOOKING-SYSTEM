package com.docin.controller;

import com.docin.dto.BaseResponse;
import com.docin.dto.DokterRequest;
import com.docin.dto.DokterResponse;
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

    @PostMapping
    public ResponseEntity<BaseResponse<DokterResponse>> createDokter(@RequestBody @Valid DokterRequest request) {
        DokterResponse dokter = dokterService.createDokter(request);
        return ResponseEntity.ok(
                BaseResponse.<DokterResponse>builder()
                        .success(true)
                        .message("Dokter created successfully")
                        .data(dokter)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<DokterResponse>>> getAllDokters() {
        List<DokterResponse> dokters = dokterService.getAllDokter();
        return ResponseEntity.ok(
                BaseResponse.<List<DokterResponse>>builder()
                        .success(true)
                        .message("List of all doctors")
                        .data(dokters)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<DokterResponse>> getDokterById(@PathVariable Long id) {
        DokterResponse dokter = dokterService.getDokterById(id);
        return ResponseEntity.ok(
                BaseResponse.<DokterResponse>builder()
                        .success(true)
                        .message("Dokter found")
                        .data(dokter)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<DokterResponse>> updateDokter(@PathVariable Long id, @RequestBody @Valid DokterRequest request) {
        DokterResponse dokter = dokterService.updateDokter(id, request);
        return ResponseEntity.ok(
                BaseResponse.<DokterResponse>builder()
                        .success(true)
                        .message("Dokter updated successfully")
                        .data(dokter)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteDokter(@PathVariable Long id) {
        dokterService.deleteDokter(id);
        return ResponseEntity.ok(
                BaseResponse.<Void>builder()
                        .success(true)
                        .message("Dokter deleted successfully")
                        .data(null)
                        .build()
        );
    }
}
