package com.example.rd.semiprojectv1.controller;

import com.example.rd.semiprojectv1.domain.MemberDTO;
import com.example.rd.semiprojectv1.repository.MemberRepository;
import com.example.rd.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    // ResponseEntity 는 스프링에서 HTTP와 관련된 기능을 구현할 때 사용.
    // 상태코드, HTTP 헤더, HTTP 본문 등을 명시적으로 설정 가능
    @PostMapping("/join")
    public ResponseEntity<?> joinok(MemberDTO member) {
        ResponseEntity<?> response = ResponseEntity.badRequest().build();

        if(memberService.newMember(member))
            response = ResponseEntity.ok().build();

        return response;
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
