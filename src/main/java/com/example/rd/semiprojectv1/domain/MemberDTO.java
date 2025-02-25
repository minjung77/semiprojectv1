package com.example.rd.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

@Data//setter , getter , tostring 자동 생성
@Builder//lombok 에 있음.
public class MemberDTO {
    private String userid;
    private String passwd;
    private String name;
    private String email;
    
}
