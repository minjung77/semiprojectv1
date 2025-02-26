package com.example.rd.semiprojectv1.repository;

import com.example.rd.semiprojectv1.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardRepository {

    @Select("select bno, title, userid, regdate, thumbs, views from boards order by bno desc limit #{stnum},25")
    List<BoardDTO> selectBoard(int stnum);
}
