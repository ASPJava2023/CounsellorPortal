package com.aspa.repository;

import com.aspa.entity.Counsellor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CounsellorRepo extends JpaRepository<Counsellor, Long> {
    Optional<Counsellor> findByEmail(String email);
}
