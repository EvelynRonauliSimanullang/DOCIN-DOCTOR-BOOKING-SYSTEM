package com.docin.controller;

import com.docin.dto.BaseResponse;
import com.docin.dto.BookingRequest;
import com.docin.dto.BookingResponse;
import com.docin.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BaseResponse<BookingResponse>> createBooking(@Valid @RequestBody BookingRequest request) {
        BookingResponse booking = bookingService.createBooking(request);
        return ResponseEntity.ok(
                BaseResponse.<BookingResponse>builder()
                        .success(true)
                        .message("Booking successful!")
                        .data(booking)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<BookingResponse>>> getMyBookings(@RequestParam String username) {
        List<BookingResponse> bookings = bookingService.getMyBookings(username);
        return ResponseEntity.ok(
                BaseResponse.<List<BookingResponse>>builder()
                        .success(true)
                        .message("List of your bookings")
                        .data(bookings)
                        .build()
        );
    }
}