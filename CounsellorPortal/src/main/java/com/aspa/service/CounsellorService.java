package com.aspa.service;


import com.aspa.dto.CounsellorDto;
import java.util.Optional;

public interface CounsellorService {
    CounsellorDto register(CounsellorDto dto);
    Optional<CounsellorDto> login(String email, String pwd);
    CounsellorDto findById(Long id);
}

