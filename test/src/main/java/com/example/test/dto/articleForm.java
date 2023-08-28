package com.example.test.dto;

import com.example.test.entity.Article;

public class articleForm {
    private String title;
    private String content;

    public articleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "articleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

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
