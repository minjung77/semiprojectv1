package com.example.rd.semiprojectv1.board;

import com.example.rd.semiprojectv1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BoardControllerTest {
    private final MockMvc mockMvc;
    private final BoardRepository boardMapper;

    @Test
    @DisplayName("/list GET request test")
    public void list() throws Exception {
        //Given
        String cpg = "1";//출력할 페이지 지정

        //When
        mockMvc.perform(get("/board/list")
                        .param("cpg", cpg))
                .andExpect(status().isOk())//상태가 ok이면
                .andDo(print());
    }
}
