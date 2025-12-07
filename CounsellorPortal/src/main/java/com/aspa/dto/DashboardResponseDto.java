package com.aspa.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DashboardResponseDto {
    private Long totalEnquiries;
    private Long newEnquiries;
    private Long enrolledEnquiries;
    private Long lostEnquiries;
}

