package com.aspa.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnquiryDto {
    private Long enqId;
    private String stuName;
    private String stuPhno;
    private String classMode;
    private Long courseId;
    private String enqStatus;
    private Long counsellorId;
    private String courseName;
}
