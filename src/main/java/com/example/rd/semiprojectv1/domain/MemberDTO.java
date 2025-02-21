package com.example.rd.semiprojectv1.domain;

import lombok.Data;

@Data//setter , getter , tostring 자동 생성
public class MemberDTO {
    private String userid;
    private String passwd;
    private String reppasswd;
    private String name;
    private String email;
    
}
