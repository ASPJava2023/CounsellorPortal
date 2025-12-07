package com.aspa.dto;


import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CounsellorDto {
    private Long counsellorId;
    private String name;
    private String email;
    private String phno;
    private String pwd;
}

