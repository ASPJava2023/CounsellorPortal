package com.aspa.service;

import com.aspa.dto.DashboardResponseDto;
import com.aspa.dto.EnqFilterRequestDto;
import com.aspa.dto.EnquiryDto;

import java.util.List;

public class EnquiryServiceImpl implements EnquiryService {
    @Override
    public EnquiryDto addEnquiry(EnquiryDto dto) {

        return null;
    }

    @Override
    public List<EnquiryDto> getEnquiriesByFilter(EnqFilterRequestDto filter) {
        return List.of();
    }

    @Override
    public EnquiryDto updateEnquiry(EnquiryDto dto) {
        return null;
    }

    @Override
    public DashboardResponseDto getDashboardStats(Long counsellorId) {
        return null;
    }
}
