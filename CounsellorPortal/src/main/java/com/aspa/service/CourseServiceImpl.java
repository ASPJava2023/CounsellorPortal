package com.aspa.service;

import com.aspa.entity.Course;
import com.aspa.repository.CourseRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {
    private final CourseRepo repo;

    @Override
    public List<Course> listAll() {
        List<Course> all = repo.findAll();
        int size = all == null ? 0 : all.size();
        log.debug("CourseService.listAll() - found {} courses", size);
        if (all == null || all.isEmpty()) {
            log.info("No courses found; seeding default courses into DB");
            List<Course> defaults = Arrays.asList(
                    Course.builder().courseName("Java Development").build(),
                    Course.builder().courseName("Web Development").build(),
                    Course.builder().courseName("Data Science").build()
            );
            repo.saveAll(defaults);
            all = repo.findAll();
            log.info("After seeding, course count = {}", all.size());
        }
        return all;
    }
}
