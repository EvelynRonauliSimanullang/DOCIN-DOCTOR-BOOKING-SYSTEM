package com.docin.controller;

import com.docin.dto.AdminLoginRequest;
import com.docin.dto.BaseResponse;
import com.docin.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<String>> login(@RequestBody @Valid AdminLoginRequest request) {
        String token = adminService.login(request);
        return ResponseEntity.ok(
                BaseResponse.<String>builder()
                        .success(true)
                        .message("Login Admin successful!")
                        .data(token)
                        .build()
        );
    }
}
