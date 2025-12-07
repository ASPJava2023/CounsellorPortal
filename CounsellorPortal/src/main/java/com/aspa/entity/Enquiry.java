package com.aspa.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "enquiries_tbl")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Enquiry {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enqId;

    private String stuName;
    private String stuPhno;
    private String classMode; // e.g., Online/Offline
    private String enqStatus; // New/Enrolled/Lost

    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counsellor_id")
    private Counsellor counsellor;

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDateTime.now();
        lastUpdated = dateCreated;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = LocalDateTime.now();
    }
}

