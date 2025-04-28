package com.docin.service.impl;

import com.docin.dto.BookingRequest;
import com.docin.dto.BookingResponse;
import com.docin.entity.Booking;
import com.docin.entity.Pasien;
import com.docin.repository.BookingRepository;
import com.docin.repository.PasienRepository;
import com.docin.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final PasienRepository pasienRepository;

    @Override
    public BookingResponse createBooking(BookingRequest request) {
        Pasien pasien = pasienRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Pasien not found"));

        Booking booking = Booking.builder()
                .pasien(pasien)
                .dokterName(request.getDokterName())
                .bookingDate(request.getBookingDate())
                .build();

        bookingRepository.save(booking);

        return mapToResponse(booking);
    }

    @Override
    public List<BookingResponse> getMyBookings(String username) {
        Pasien pasien = pasienRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Pasien not found"));

        List<Booking> bookings = bookingRepository.findByPasien(pasien);

        return bookings.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private BookingResponse mapToResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .dokterName(booking.getDokterName())
                .bookingDate(booking.getBookingDate())
                .pasienUsername(booking.getPasien().getUsername())
                .build();
    }
}