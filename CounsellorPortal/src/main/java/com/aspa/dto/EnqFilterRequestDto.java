package com.aspa.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EnqFilterRequestDto {
    private Long counsellorId;
    private Long courseId;
    private String status; // New / Enrolled / Lost
    private String classMode;
}

