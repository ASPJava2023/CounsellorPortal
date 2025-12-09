package com.aspa.controller;
import com.aspa.dto.DashboardResponseDto;
import com.aspa.service.EnquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final EnquiryService enquiryService;

    @GetMapping("/dashboard")
    public String dashboard(@RequestParam Long cId, Model model) {
        DashboardResponseDto stats = enquiryService.getDashboardStats(cId);
        model.addAttribute("stats", stats);
        model.addAttribute("counsellorId", cId);
        return "dashboard";
    }
}
