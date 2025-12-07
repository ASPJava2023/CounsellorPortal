package com.aspa.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "courses_tbl")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false, unique = true)
    private String courseName;
}
