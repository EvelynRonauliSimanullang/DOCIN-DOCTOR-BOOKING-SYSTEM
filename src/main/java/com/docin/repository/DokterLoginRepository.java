package com.docin.repository;

import com.docin.entity.Dokter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DokterLoginRepository extends JpaRepository<Dokter, Long> {
    Optional<Dokter> findByUsername(String username); // <--- untuk login
}