package com.spring.e0723.dto;

import com.spring.e0723.entity.Article;
import com.spring.e0723.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class MemberForm {
    private String email;
    private String password;

    /**
     * DTO를 ENTITY로 변경하는 메소드
     */
    public Member toEntity() {
        return new Member(null, email, password);
    }




}
