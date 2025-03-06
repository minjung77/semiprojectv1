package com.example.rd.semiprojectv1.service;

import com.example.rd.semiprojectv1.domain.*;

import java.util.List;

public interface BoardService {

    List<BoardDTO> readBoard(int cpg);

    int countBoard();

    List<BoardDTO> findBoard(int cpg, String findtype, String findkey);

    int countfindBoard(String findtype, String findkey);

    Board readOneBoard(int bno);

    void readOneView(int bno);

    boolean newBoard(NewBoardDTO newBoardDTO);

    boolean newReply(NewReplyDTO newReplyDTO);

    List<Reply> readReply(int bno);

    boolean newComment(NewReplyDTO newReplyDTO);
}
