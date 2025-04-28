package com.docin.service;

import com.docin.dto.BookingRequest;
import com.docin.dto.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest request);
    List<BookingResponse> getMyBookings(String username);
}