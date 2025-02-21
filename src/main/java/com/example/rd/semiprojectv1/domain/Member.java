package com.example.rd.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data//setter , getter , tostring 자동 생성
@Builder//lombok 에 있음.
public class Member {
    private int mno;
    private String userid;
    private String passwd;
    private String reppasswd;
    private String name;
    private String email;
    private LocalDateTime regdate;
}
