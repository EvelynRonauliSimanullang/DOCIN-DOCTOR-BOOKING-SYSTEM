package com.docin.service;

import com.docin.dto.*;

public interface PasienService {
    void register(PasienRequest request);
    String login(LoginRequest request);
    void logout(String username);
    String generateOtp(String username);
    String resetPassword(ResetPasswordRequest request);
}