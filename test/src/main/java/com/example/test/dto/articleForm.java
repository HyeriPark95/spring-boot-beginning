package com.example.test.dto;

import com.example.test.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor //생성자 어노테이션
@ToString //toSting 메서드 어노테이션
public class articleForm {

    private String title;
    private String content;

    public Article toEntity(){
        return new Article(null,title,content);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
