package com.example.test.dto;

import com.example.test.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor //생성자 어노테이션
@ToString //toSting 메서드 어노테이션
public class articleForm {  //사용자로부터 view로부터 정보를 받아올 때에 사용됨 (DTO역할)

    private Long id;  //id필드 추가함
    private String title;
    private String content;

    public Article toEntity(){
        return new Article(id,title,content);
    }

    }
