package com.aspa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "counsellors_tbl")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Counsellor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long counsellorId;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String pwd;
    private String phno;

    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "counsellor")
    private List<Enquiry> enquiries;

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
