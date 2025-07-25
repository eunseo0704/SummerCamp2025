package com.spring.e0723.dto;

import com.spring.e0723.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class ArticleForm {
    private String title;
    private String content;



    /** DTO를 ENTITY로 변경하는 메소드 */
    public Article toEntity() {
        return new Article(null, title, content);
    }




}
