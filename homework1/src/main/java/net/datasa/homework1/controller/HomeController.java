package net.datasa.homework1.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import net.datasa.homework1.dto.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");
        model.addAttribute("loginId", loginId);
        return "home";  // home.html
    }
}

