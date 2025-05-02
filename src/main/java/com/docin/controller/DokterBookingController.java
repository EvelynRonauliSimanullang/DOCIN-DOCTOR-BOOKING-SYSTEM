package com.docin.controller;

import com.docin.dto.BaseResponse;
import com.docin.dto.DokterBookingResponse;
import com.docin.service.DokterBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/dokters/bookings")
@RequiredArgsConstructor
public class DokterBookingController {

    private final DokterBookingService dokterBookingService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<DokterBookingResponse>>> getMyBookings(@AuthenticationPrincipal Principal principal) {

        Long dokterId = Long.parseLong(principal.getName()); 
        List<DokterBookingResponse> bookings = dokterBookingService.getBookingsByDokter(dokterId);
        return ResponseEntity.ok(
                BaseResponse.<List<DokterBookingResponse>>builder()
                        .success(true)
                        .message("List booking untuk dokter")
                        .data(bookings)
                        .build()
        );
    }
}

