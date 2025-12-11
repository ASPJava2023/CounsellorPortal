package com.aspa.service;

import com.aspa.dto.DashboardResponseDto;
import com.aspa.dto.EnqFilterRequestDto;
import com.aspa.dto.EnquiryDto;
import com.aspa.entity.Course;
import com.aspa.entity.Counsellor;
import com.aspa.entity.Enquiry;
import com.aspa.repository.CourseRepo;
import com.aspa.repository.CounsellorRepo;
import com.aspa.repository.EnquiryRepo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnquiryServiceImpl implements EnquiryService {
    private final EnquiryRepo enquiryRepo;
    private final CourseRepo courseRepo;
    private final CounsellorRepo counsellorRepo;

    @Override
    public EnquiryDto addEnquiry(EnquiryDto dto) {
        if (dto.getEnqId() != null) {
            return updateEnquiry(dto);
        }
        Course course = null;
        if (dto.getCourseId() != null) {
            course = courseRepo.findById(dto.getCourseId()).orElse(null);
        }
        Counsellor counsellor = null;
        if (dto.getCounsellorId() != null) {
            counsellor = counsellorRepo.findById(dto.getCounsellorId()).orElse(null);
        }

        Enquiry enq = Enquiry.builder()
                .stuName(dto.getStuName())
                .stuPhno(dto.getStuPhno())
                .classMode(dto.getClassMode())
                .enqStatus(dto.getEnqStatus() == null ? "New" : dto.getEnqStatus())
                .course(course)
                .counsellor(counsellor)
                .build();

        Enquiry saved = enquiryRepo.save(enq);
        return mapToDto(saved);
    }

    @Override
    public List<EnquiryDto> getEnquiriesByFilter(EnqFilterRequestDto filter) {
        Enquiry enq = new Enquiry();

        if (filter.getCounsellorId() != null) {
            Counsellor c = new Counsellor();
            c.setCounsellorId(filter.getCounsellorId());
            enq.setCounsellor(c);
        }

        if (filter.getCourseId() != null) {
            Course c = new Course();
            c.setCourseId(filter.getCourseId());
            enq.setCourse(c);
        }

        if (filter.getClassMode() != null && !filter.getClassMode().isEmpty()) {
            enq.setClassMode(filter.getClassMode());
        }

        if (filter.getStatus() != null && !filter.getStatus().isEmpty()) {
            enq.setEnqStatus(filter.getStatus());
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Enquiry> example = Example.of(enq, matcher);

        List<Enquiry> list = enquiryRepo.findAll(example);

        return list.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public EnquiryDto updateEnquiry(EnquiryDto dto) {
        if (dto == null || dto.getEnqId() == null)
            return null;
        Optional<Enquiry> opt = enquiryRepo.findById(dto.getEnqId());
        if (opt.isEmpty())
            return null;
        Enquiry enq = opt.get();

        enq.setStuName(dto.getStuName());
        enq.setStuPhno(dto.getStuPhno());
        enq.setClassMode(dto.getClassMode());
        if (dto.getEnqStatus() != null)
            enq.setEnqStatus(dto.getEnqStatus());
        if (dto.getCourseId() != null) {
            courseRepo.findById(dto.getCourseId()).ifPresent(enq::setCourse);
        }
        if (dto.getCounsellorId() != null) {
            counsellorRepo.findById(dto.getCounsellorId()).ifPresent(enq::setCounsellor);
        }

        Enquiry saved = enquiryRepo.save(enq);
        return mapToDto(saved);
    }

    @Override
    public EnquiryDto getEnquiryById(Long enqId) {
        return enquiryRepo.findById(enqId).map(this::mapToDto).orElse(null);
    }

    @Override
    public DashboardResponseDto getDashboardStats(Long counsellorId) {
        List<Enquiry> list = enquiryRepo.findByCounsellorCounsellorId(counsellorId);
        long total = list.size();
        long news = list.stream().filter(e -> "New".equalsIgnoreCase(e.getEnqStatus())).count();
        long enrolled = list.stream().filter(e -> "Enrolled".equalsIgnoreCase(e.getEnqStatus())).count();
        long lost = list.stream().filter(e -> "Lost".equalsIgnoreCase(e.getEnqStatus())).count();

        return DashboardResponseDto.builder()
                .totalEnquiries(total)
                .newEnquiries(news)
                .enrolledEnquiries(enrolled)
                .lostEnquiries(lost)
                .build();
    }

    private EnquiryDto mapToDto(Enquiry e) {
        if (e == null)
            return null;
        return EnquiryDto.builder()
                .enqId(e.getEnqId())
                .stuName(e.getStuName())
                .stuPhno(e.getStuPhno())
                .classMode(e.getClassMode())
                .courseId(e.getCourse() != null ? e.getCourse().getCourseId() : null)
                .courseName(e.getCourse() != null ? e.getCourse().getCourseName() : null)
                .enqStatus(e.getEnqStatus())
                .counsellorId(e.getCounsellor() != null ? e.getCounsellor().getCounsellorId() : null)
                .build();
    }
}
