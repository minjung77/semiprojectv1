package com.example.rd.semiprojectv1.service;

import com.example.rd.semiprojectv1.domain.BoardDTO;
import com.example.rd.semiprojectv1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardMapper;

    @Override
    public List<BoardDTO> readBoard() {
        return boardMapper.selectBoard();
    }
}
