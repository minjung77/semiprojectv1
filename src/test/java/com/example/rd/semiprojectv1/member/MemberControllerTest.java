package com.example.rd.semiprojectv1.member;

import com.example.rd.semiprojectv1.controller.MemberController;
import com.example.rd.semiprojectv1.domain.Member;
import com.example.rd.semiprojectv1.repository.MemberRepository;
import com.example.rd.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class MemberControllerTest {
    private final MockMvc mockMvc;
    private final MemberRepository memberMapper;

    @Test
    @DisplayName("/join POST request test")
    public void joinOk() throws Exception {
        //Given
        String userid ="abc1232";
        String passwd ="987xyz";
        String name ="abc1232";
        String email ="abc123@gmail.com";
        //When
        mockMvc.perform(post("/member/join")
                    .param("userid",userid)
                    .param("passwd",passwd)
                    .param("name",name)
                    .param("email",email))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/member/login"));

        //Then
//        Member member = memberMapper.findByUserId(userid);
//        assertThat(member).isNull();
//        assertThat(member.getMno()).isNull();
//        assertThat(member.getRegdate()).isNull();
//        assertThat(member.getName()).isEqualTo("abc123");
//        assertThat(member.getEmail()).isEqualTo("abc1232gmail.com");
    }
}
