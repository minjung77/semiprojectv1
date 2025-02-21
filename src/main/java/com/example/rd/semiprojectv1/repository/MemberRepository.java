package com.example.rd.semiprojectv1.repository;

import com.example.rd.semiprojectv1.domain.Member;
import com.example.rd.semiprojectv1.domain.MemberDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberRepository {
    @Insert("insert into members(userid, passwd, name, email) values(#{userid},#{passwd},#{name},#{email})")
    int insertMember(MemberDTO dto);

    @Select("select * from members where userid = #{userid}")
    Member findByUserId(String userid);
}
