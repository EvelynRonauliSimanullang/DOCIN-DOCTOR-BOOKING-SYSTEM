package com.docin.service;

import com.docin.dto.DokterProfileResponse;

public interface DokterProfileService {
    DokterProfileResponse getProfile(Long dokterId);
}