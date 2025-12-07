package com.aspa.controller;
import com.aspa.dto.EnqFilterRequestDto;
import com.aspa.dto.EnquiryDto;
import com.aspa.service.CourseService;
import com.aspa.service.EnquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/enquiries")
public class EnquiryController {
    private final EnquiryService enquiryService;
    private final CourseService courseService;

    @GetMapping("/add")
    public String addEnqPage(@RequestParam Long cId, Model model) {
        model.addAttribute("enquiry", new EnquiryDto());
        model.addAttribute("courses", courseService.listAll());
        model.addAttribute("counsellorId", cId);
        return "add-enq";
    }

    @PostMapping("/save")
    public String saveEnquiry(@ModelAttribute EnquiryDto dto) {
        enquiryService.addEnquiry(dto);
        return "redirect:/dashboard?cId=" + dto.getCounsellorId();
    }

    @GetMapping("/view")
    public String viewEnqs(@RequestParam Long cId, Model model) {
        EnqFilterRequestDto filter = EnqFilterRequestDto.builder().counsellorId(cId).build();
        List<EnquiryDto> list = enquiryService.getEnquiriesByFilter(filter);
        model.addAttribute("enqs", list);
        model.addAttribute("courses", courseService.listAll());
        model.addAttribute("counsellorId", cId);
        return "view-enqs";
    }

    @GetMapping("/edit/{id}")
    public String editEnq(@PathVariable Long id, Model m) {
        // load enquiry and courses
        // omitted for brevity — implement findById in service
        return "add-enq";
    }
}

