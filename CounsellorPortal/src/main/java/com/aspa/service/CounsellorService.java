package com.aspa.service;


import com.aspa.dto.CounsellorDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CounsellorService {
    CounsellorDto register(CounsellorDto dto);
    Optional<CounsellorDto> login(String email, String pwd);
    CounsellorDto findById(Long id);
}

