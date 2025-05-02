package com.docin.repository;

import com.docin.entity.Booking;
import com.docin.entity.Pasien;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByPasien(Pasien pasien);
    List<Booking> findByDokterId(Long dokterId);
}