package com.docin.controller;

import com.docin.dto.BaseResponse;
import com.docin.dto.DokterProfileResponse;
import com.docin.service.DokterProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/dokters")
@RequiredArgsConstructor
public class DokterProfileController {

    private final DokterProfileService dokterProfileService;

    @GetMapping("/profile")
    public ResponseEntity<BaseResponse<DokterProfileResponse>> getProfile(@AuthenticationPrincipal Principal principal) {
        Long dokterId = Long.parseLong(principal.getName());
        DokterProfileResponse profile = dokterProfileService.getProfile(dokterId);
        return ResponseEntity.ok(
                BaseResponse.<DokterProfileResponse>builder()
                        .success(true)
                        .message("Profile dokter ditemukan")
                        .data(profile)
                        .build()
        );
    }
}
