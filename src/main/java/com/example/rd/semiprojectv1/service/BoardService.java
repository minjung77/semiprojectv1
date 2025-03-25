package com.example.rd.semiprojectv1.service;

import com.example.rd.semiprojectv1.domain.BoardListDTO;
import com.example.rd.semiprojectv1.domain.BoardReplyDTO;
import com.example.rd.semiprojectv1.domain.NewBoardDTO;
import com.example.rd.semiprojectv1.domain.NewReplyDTO;

import java.util.Map;

public interface BoardService {

    BoardListDTO readBoard(int cpg);

    BoardReplyDTO readOneBoardReply(int bno);

    BoardListDTO findBoard(int cpg, String findtype, String findkey);

    int countfindBoard(Map<String, Object> params);

    //Board readOneBoard(int bno);

    //void readOneView(int bno);

    boolean newBoard(NewBoardDTO newBoardDTO);

    boolean newReply(NewReplyDTO newReplyDTO);

    //List<Reply> readReply(int pno);

    boolean newComment(NewReplyDTO newReplyDTO);
}
