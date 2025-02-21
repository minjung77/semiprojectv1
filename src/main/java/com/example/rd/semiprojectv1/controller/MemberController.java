package com.example.rd.semiprojectv1.controller;

import com.example.rd.semiprojectv1.domain.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/join")
    public String join() {
        return "views/member/join";
    }
    @PostMapping("/join")
    public String joinok(MemberDTO member) {
        log.info("submit된 회원 정보 : {}", member);
        return "redirect:/member/login";
    }
    @GetMapping("/login")
    public String login() {
        return "views/member/login";
    }
    @GetMapping("/info")
    public String info() {
        return "views/member/myinfo";
    }
}
