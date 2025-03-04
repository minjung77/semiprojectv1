package com.example.rd.semiprojectv1.service;

import com.example.rd.semiprojectv1.domain.Board;
import com.example.rd.semiprojectv1.domain.BoardDTO;
import com.example.rd.semiprojectv1.domain.NewBoardDTO;
import com.example.rd.semiprojectv1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardMapper;
    @Value("${board.page-size}") private int pageSize;

    @Override
    public List<BoardDTO> readBoard(int cpg) {
        //cpg에 따라 시작위치 계산
        int stnum = (cpg - 1) * pageSize;
        return boardMapper.selectBoard(stnum, pageSize);
    }

    @Override
    public int countBoard() {
        return boardMapper.countPageBoard(pageSize);
    }

    @Override
    public List<BoardDTO> findBoard(int cpg, String findtype, String findkey) {
        Map<String, Object> params = new HashMap<>();
        params.put("stnum", (cpg - 1) * pageSize);
        params.put("pageSize", pageSize);
        params.put("findtype", findtype);
        params.put("findkey", findkey);

        return boardMapper.selectFindBoard(params);
    }

    @Override
    public int countfindBoard(String findtype, String findkey) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageSize", pageSize);
        params.put("findtype", findtype);
        params.put("findkey", findkey);

        return boardMapper.countFindBoard(params);
    }

    @Override
    public Board readOneBoard(int bno) {
        return boardMapper.selectOneSelect(bno);
    }

    @Override
    public void readOneView(int bno) {
        boardMapper.updateViewOne(bno);
    }

    @Override
    public boolean newBoard(NewBoardDTO newBoardDTO) {
        int result = boardMapper.insertBoard(newBoardDTO);

        return result > 0;
    }

}
