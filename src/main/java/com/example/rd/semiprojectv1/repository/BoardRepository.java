package com.example.rd.semiprojectv1.repository;

import com.example.rd.semiprojectv1.domain.Board;
import com.example.rd.semiprojectv1.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardRepository {

    @Select("select bno, title, userid, regdate, thumbs, views from boards order by bno desc limit #{stnum}, #{pageSize}")
    List<BoardDTO> selectBoard(int stnum, int pageSize);

    @Select("select ceil(count(bno) / #{pageSize}) cntpg from boards")
    int countPageBoard(int pageSize);

//    List<BoardDTO> selectFindBoard(int stnum, int pageSize, String findtype, String findkey);
    List<BoardDTO> selectFindBoard(Map<String, Object> params);

    int countFindBoard(Map<String, Object> params);

    @Select("select * from boards where bno = #{bno}")
    Board selectOneSelect(int bno);
}
