package com.docin.service;

import com.docin.dto.AdminPasienRequest;
import com.docin.dto.AdminPasienResponse;

import java.util.List;

public interface AdminPasienService {
    AdminPasienResponse createPasien(AdminPasienRequest request);
    AdminPasienResponse updatePasien(Long id, AdminPasienRequest request);
    void deletePasien(Long id);
    AdminPasienResponse getPasienById(Long id);
    List<AdminPasienResponse> getAllPasien();
}

