package com.example.rd.semiprojectv1.member;

import com.example.rd.semiprojectv1.domain.MemberDTO;
import com.example.rd.semiprojectv1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@MybatisTest
@RequiredArgsConstructor // final 필드변수로 생성자 생성
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)//생성자 주입시 필요
public class MemberMapperTest {

    //autowired 가 아닌 생성자를 이용한 의존성 주입 사용(@ RequiredArgsConstructor)
    private final MemberRepository memberMapper;

        @Test
    @DisplayName("MemberMapper Insert test")
    void insertTest(){
        //Given : 테스트에 사용할 데이터 제공
        MemberDTO dto = MemberDTO.builder()
                .userid("abc1234")
                .passwd("987zyx")
                .name("abc123")
                .email("abc123@gmail.com")
                .build();

        //When : 데이터로 테스트할 기능 호출
        int result = memberMapper.insertMember(dto);

        //Then : 호출되고 난 후 결과값 확인
        log.info("result : {}", result);
        assertThat(result).isEqualTo(1); // 1이면 :true
    }
}
