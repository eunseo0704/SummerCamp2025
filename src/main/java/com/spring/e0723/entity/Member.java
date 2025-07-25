package com.spring.e0723.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    @Id // 이변수가 PK라고 선언하는 골벵이
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String content;



}
