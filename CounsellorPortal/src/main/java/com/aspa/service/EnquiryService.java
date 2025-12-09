package com.aspa.service;
import com.aspa.dto.DashboardResponseDto;
import com.aspa.dto.EnqFilterRequestDto;
import com.aspa.dto.EnquiryDto;
import java.util.List; // added import so List is resolved

public interface EnquiryService {
    EnquiryDto addEnquiry(EnquiryDto dto);
    List<EnquiryDto> getEnquiriesByFilter(EnqFilterRequestDto filter);
    EnquiryDto updateEnquiry(EnquiryDto dto);
    DashboardResponseDto getDashboardStats(Long counsellorId);
}
