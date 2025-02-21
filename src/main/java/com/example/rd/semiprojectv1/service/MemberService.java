package com.example.rd.semiprojectv1.service;

import com.example.rd.semiprojectv1.domain.MemberDTO;
import com.example.rd.semiprojectv1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberMapper;

    public boolean newMember(MemberDTO member) {
        int result = memberMapper.insertMember(member);
        return result == 1;//회원정보가 테이블 저장되었는지 여부에 따라 true/false 반환
    }
}
