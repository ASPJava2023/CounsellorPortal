package com.aspa.controller;
import com.aspa.dto.CounsellorDto;
import com.aspa.service.CounsellorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final CounsellorService counsellorService;

    @GetMapping({"/", "/login"})
    public String loginPage() { return "index"; }

    @GetMapping("/register")
    public String registerPage() { return "register"; }

    @PostMapping("/register")
    public String register(@ModelAttribute CounsellorDto dto, Model m) {
        counsellorService.register(dto);
        m.addAttribute("msg", "Registration successful. Please login.");
        return "index";
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam String email, @RequestParam String pwd, Model m) {
        var opt = counsellorService.login(email, pwd);
        if (opt.isPresent()) {
            // store counsellorId in session for simplicity (not secure for prod)
            m.addAttribute("counsellor", opt.get());
            return "redirect:/dashboard?cId=" + opt.get().getCounsellorId();
        } else {
            m.addAttribute("error", "Invalid credentials");
            return "index";
        }
    }
}

