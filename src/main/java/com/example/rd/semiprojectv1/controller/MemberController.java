package com.example.rd.semiprojectv1.controller;

import com.example.rd.semiprojectv1.domain.Member;
import com.example.rd.semiprojectv1.domain.MemberDTO;
import com.example.rd.semiprojectv1.jwt.JwtTokenProvider;
import com.example.rd.semiprojectv1.repository.MemberRepository;
import com.example.rd.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/join")
    public String join() {
        return "views/member/join";
    }

    // ResponseEntity 는 스프링에서 HTTP와 관련된 기능을 구현할 때 사용.
    // 상태코드, HTTP 헤더, HTTP 본문 등을 명시적으로 설정 가능
    @PostMapping("/join")
    public ResponseEntity<?> joinok(MemberDTO member) {
        // 회원 가입 처리시 기타 오류 발생에 대한 응답코드 설정
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        try{
            // 정상 처리시 상태코드 200으로 응답
            memberService.newMember(member);
            response = ResponseEntity.ok().build();
        }catch(IllegalStateException e){
            // 비 정상 처리시 상태코드 400으로 응답 - 클라이언트 잘못
            // 중복 아이디나 중복 이메일 사용시
            response = ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // 비정상 처리시 상태코드 500으로 응답 - 서버 잘못
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/login")
    public String login() {
        return "views/member/login";
    }

    //스프링 시큐리티가 자동으로 처리 - 생략
    @PostMapping("/login")
    public ResponseEntity<?> loginok(MemberDTO member, HttpServletResponse res) {
        // 로그인 처리시 기타 오류 발생에 대한 응답코드 설정
        ResponseEntity<?> response = ResponseEntity.internalServerError().body("서버 처리시 오류가 발생했습니다.");

        log.info("submit 정보 : {}", member);

        try{
            // 정상 처리시 상태코드 200으로 응답
//            Member loginUser = memberService.loginMember(member);
//            session.setAttribute("loginUser", loginUser);
//            session.setMaxInactiveInterval(600); // 세션 유지 10분


            //스프링 시큐리티에서 제공하는 인증처리 매니저로 인증 처리
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(member.getUserid(), member.getPasswd())
            );

            //인증이 완료된 경우 jwt 토큰 생성
            final String jwt = jwtTokenProvider.generateToken(member.getUserid());

            // JWT 토큰을 쿠키에 저장
            Cookie cookie = new Cookie("jwt", jwt);
            cookie.setHttpOnly(true);// 토큰은 header를 통해서만 서버로 전송가능
            cookie.setMaxAge(60 * 30); // 유효시간 : 30분
            cookie.setPath("/");
            res.addCookie(cookie);

            response = ResponseEntity.ok().body("로그인 성공했습니다.");
        }catch(BadCredentialsException e){
            // 인증 실패시 상태코드 400으로 응답 - 아이디나 비밀번호 잘못 입력시
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디나 비밀번호를 확인하세요");
            log.info(e.getMessage());
        } catch (Exception e) {
            // 비정상 처리시 상태코드 500으로 응답 - 서버 잘못
            log.info(e.getMessage());
        }
        return response;
    }

    @GetMapping("/myinfo")
    public String info(Authentication authentication, Model model) {
        String returnUrl = "views/member/login";

        //로그인 인증이 성공했다면
        if(authentication != null && authentication.isAuthenticated()) {
            //인증 완료된 사용자 정보(아이디)를 가져옴
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            //그 외 사용자 정보(이름, 이메일, 가입일)를 가져오기 위해 다시 사용자 테이블 조회
            model.addAttribute("loginUser", memberService.findByUserid(userDetails));

            returnUrl = "views/member/myinfo";
        }
        return returnUrl;
    }

    //스프링 시큐리티가 자동으로 처리 - 생략
    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse res) {
//        session.invalidate();//세션 제거

        Cookie cookie = new Cookie("jwt", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        res.addCookie(cookie);

        // 세션 무효화
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @GetMapping("/loginfail")
    public String loginfail() {
        return "views/member/loginfail";
    }
}


