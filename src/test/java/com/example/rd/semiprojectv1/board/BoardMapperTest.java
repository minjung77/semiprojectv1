package com.example.rd.semiprojectv1.board;

import com.example.rd.semiprojectv1.domain.BoardDTO;
import com.example.rd.semiprojectv1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.TestConstructor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@MybatisTest
@RequiredArgsConstructor // final 필드변수로 생성자 생성
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)//생성자 주입시 필요
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//h2 데이터베이스 사용중지
public class BoardMapperTest {

    //autowired 가 아닌 생성자를 이용한 의존성 주입 사용(@ RequiredArgsConstructor)
    private final BoardRepository boardMapper;

    @Test
    @DisplayName("BoardMapper select test")
    void insertTest(){
        //Given : 테스트에 사용할 데이터 제공

        //When : 데이터로 테스트할 기능 호출
        List<BoardDTO> results = boardMapper.selectBoard();

        //Then : 호출되고 난 후 결과값 확인
        log.info("results : {}", results);
        assertNotNull(results);
    }
}
