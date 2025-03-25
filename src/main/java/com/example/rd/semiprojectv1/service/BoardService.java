package com.example.rd.semiprojectv1.service;

import com.example.rd.semiprojectv1.domain.*;

import java.util.List;
import java.util.Map;

public interface BoardService {

    BoardListDTO readBoard(int cpg);

//    int countBoard();

    BoardReplyDTO readOneBoardReply(int bno);

//    List<BoardDTO> findBoard(int cpg, String findtype, String findkey);
    BoardListDTO findBoard(int cpg, String findtype, String findkey);

//    int countfindBoard(String findtype, String findkey);
    int countfindBoard(Map<String, Object> params);

//    Board readOneBoard(int bno);

//    void readOneView(int bno);

    boolean newBoard(NewBoardDTO newBoardDTO);

    boolean newReply(NewReplyDTO newReplyDTO);

    List<Reply> readReply(int bno);

    boolean newComment(NewReplyDTO newReplyDTO);
}
