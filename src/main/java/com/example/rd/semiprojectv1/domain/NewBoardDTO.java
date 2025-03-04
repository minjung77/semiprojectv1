package com.example.rd.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NewBoardDTO {
    private String title;
    private String userid;
    private String contents;
}
