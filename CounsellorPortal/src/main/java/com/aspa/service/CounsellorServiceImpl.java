package com.aspa.service;
import com.aspa.dto.CounsellorDto;
import com.aspa.entity.Counsellor;
import com.aspa.repository.CounsellorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CounsellorServiceImpl implements CounsellorService {
    private final CounsellorRepo repo;

    @Override
    public CounsellorDto register(CounsellorDto dto) {
        Counsellor c = Counsellor.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .pwd(dto.getPwd())
                .phno(dto.getPhno())
                .build();
        Counsellor saved = repo.save(c);
        dto.setCounsellorId(saved.getCounsellorId());
        return dto;
    }

    @Override
    public Optional<CounsellorDto> login(String email, String pwd) {
        return repo.findByEmail(email)
                .filter(c -> c.getPwd().equals(pwd))
                .map(c -> CounsellorDto.builder()
                        .counsellorId(c.getCounsellorId())
                        .email(c.getEmail())
                        .name(c.getName())
                        .phno(c.getPhno())
                        .build());
    }

    @Override
    public CounsellorDto findById(Long id) {
        return repo.findById(id).map(c -> CounsellorDto.builder()
                .counsellorId(c.getCounsellorId())
                .name(c.getName())
                .email(c.getEmail())
                .phno(c.getPhno())
                .build()).orElse(null);
    }
}

