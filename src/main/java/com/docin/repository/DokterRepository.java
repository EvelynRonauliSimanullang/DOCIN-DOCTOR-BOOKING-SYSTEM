package com.docin.repository;

import com.docin.entity.Dokter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DokterRepository extends JpaRepository<Dokter, Long> {
    // Kalau mau, nanti kita bisa tambah custom query di sini.
}
