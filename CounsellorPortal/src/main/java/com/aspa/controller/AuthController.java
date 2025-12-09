package com.aspa.controller;
import com.aspa.dto.CounsellorDto;
import com.aspa.service.CounsellorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final CounsellorService counsellorService;

    @GetMapping({"/", "/login"})
    public String loginPage() { return "index"; }

    @GetMapping("/register")
    public String registerPage(Model m) {
        // Provide an empty CounsellorDto so Thymeleaf can bind the form (th:object="${counsellor}")
        m.addAttribute("counsellor", new CounsellorDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute CounsellorDto dto, Model m) {
        counsellorService.register(dto);
        m.addAttribute("msg", "Registration successful. Please login.");
        return "index";
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam String email, @RequestParam String pwd, HttpSession session, Model m) {
        var opt = counsellorService.login(email, pwd);
        if (opt.isPresent()) {
            // store counsellor in HTTP session so templates can access it across pages
            session.setAttribute("counsellor", opt.get());
            return "redirect:/dashboard?cId=" + opt.get().getCounsellorId();
        } else {
            m.addAttribute("error", "Invalid credentials");
            return "index";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
