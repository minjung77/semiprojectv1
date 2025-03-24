package com.example.rd.semiprojectv1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(unique = true, nullable = false)
    private String userid;
    @Column(unique = true)
    private String passwd;
    @Column(unique = true)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;

    @CreationTimestamp
    private LocalDateTime regdate;
}
