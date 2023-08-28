package com.example.test.controller;

import com.example.test.dto.articleForm;
import com.example.test.entity.Article;
import com.example.test.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class articleController {
    @Autowired  //자동연결
    private ArticleRepository articleRepository;  //원래는 new를 사용해서 객체 생성해야하는데 Autowired를 통해서 가능
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "/articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(articleForm form){
        System.out.println(form);
        
        //1. DTO를 Entity로 변환
        Article article = form.toEntity();
        System.out.println(article);
        //2. Repository에게 Entity를 DB안에 저장하게 함
        Article saved = articleRepository.save(article);  //저장메서드
        System.out.println(saved);
        return "";
    }

}
