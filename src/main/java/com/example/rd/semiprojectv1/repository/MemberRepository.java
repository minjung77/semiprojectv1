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

    @Select("select count(userid) from members where userid = #{userid}")
    int countByUserid(String userid);

    @Select("select count(email) from members where email = #{email}")
    int countByEmail(String email);
}
