package com.example.test.controller;

import com.example.test.dto.articleForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class articleController {
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "/articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(articleForm article){
        System.out.println(article);
        return "/articles/result";
    }

}
