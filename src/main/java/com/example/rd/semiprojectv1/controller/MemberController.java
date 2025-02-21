package com.example.rd.semiprojectv1.controller;

import com.example.rd.semiprojectv1.domain.MemberDTO;
import com.example.rd.semiprojectv1.repository.MemberRepository;
import com.example.rd.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join() {
        return "views/member/join";
    }
    @PostMapping("/join")
    public String joinok(MemberDTO member) {
        String returnPage = "redirect:/error";
        log.info("submit된 회원 정보 : {}", member);

        if(memberService.newMember(member))
            returnPage = "redirect:/member/login";

        return returnPage;
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
