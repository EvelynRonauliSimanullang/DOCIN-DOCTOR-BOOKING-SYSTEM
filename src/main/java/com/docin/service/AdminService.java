package com.docin.service;

import com.docin.dto.AdminLoginRequest;
import com.docin.dto.AdminResponse;

public interface AdminService {
    AdminResponse login(AdminLoginRequest request);
}

