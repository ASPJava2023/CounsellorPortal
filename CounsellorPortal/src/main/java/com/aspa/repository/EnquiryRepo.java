package com.aspa.repository;
import com.aspa.entity.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnquiryRepo extends JpaRepository<Enquiry, Long> {
    List<Enquiry> findByCounsellorCounsellorId(Long counsellorId);
    // add custom query methods for filters if needed
}

