package net.datasa.homework1.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import net.datasa.homework1.dto.MemberDTO;
import net.datasa.homework1.entity.MemberEntity;
import net.datasa.homework1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class MemberController {

    @Autowired
    MemberService service;

    @PostMapping("/input")
    public String input(@ModelAttribute MemberDTO dto) {
        log.debug("전달받은값 : {}",dto);
        service.insertMember(dto);
        return "redirect:/";
    }

    @GetMapping("/input")
    public String inputForm() {
        return "input";  // input.html
    }

    @GetMapping("/login")
    public String loginForm(Model model,
                            @CookieValue(value = "savedId", required = false) String savedId) {
        if (savedId != null) {
            model.addAttribute("savedId", savedId);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginDo(@ModelAttribute MemberDTO dto,
                          HttpSession session,
                          HttpServletResponse response,
                          @RequestParam(value = "rememberId", required = false) String rememberId) {

        boolean result = service.checkLogin(dto.getId(), dto.getPw());
        if (result == true) {
            session.setAttribute("loginId", dto.getId());

            if (rememberId != null) {   // 체크박스 체크된 경우
                Cookie c1 = new Cookie("savedId", dto.getId());
                c1.setMaxAge(60 * 60 * 24 * 7);  // 7일
                c1.setPath("/");
                response.addCookie(c1);
            } else {                    // 체크 안 된 경우 (쿠키 삭제)
                Cookie c2 = new Cookie("savedId", "");
                c2.setMaxAge(0);
                c2.setPath("/");
                response.addCookie(c2);
            }
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginId");
        return "redirect:/";
    }

    @GetMapping("/memberList")
    public String memberlist(Model model) {
        List<MemberEntity> m = service.listAll();
        model.addAttribute("memberList", m);
        return "memberList";
    }
}
