package com.docin.service.impl;

import com.docin.dto.DokterBookingResponse;
import com.docin.entity.Booking;
import com.docin.repository.BookingRepository;
import com.docin.service.DokterBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DokterBookingServiceImpl implements DokterBookingService {

    private final BookingRepository bookingRepository;

    @Override
    public List<DokterBookingResponse> getBookingsByDokter(Long dokterId) {
        List<Booking> bookings = bookingRepository.findByDokterId(dokterId);
        return bookings.stream().map(booking -> DokterBookingResponse.builder()
                .bookingId(booking.getId())
                .pasienUsername(booking.getPasien().getUsername())
                .tanggal(booking.getTanggal())
                .jam(booking.getJam())
                .status(booking.getStatus())
                .build()
        ).collect(Collectors.toList());
    }
}
