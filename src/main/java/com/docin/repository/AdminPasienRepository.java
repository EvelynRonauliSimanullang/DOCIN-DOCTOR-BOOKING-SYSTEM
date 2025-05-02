package com.docin.repository;

import com.docin.entity.Pasien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminPasienRepository extends JpaRepository<Pasien, Long> {
    Optional<Pasien> findByUsername(String username);
}
