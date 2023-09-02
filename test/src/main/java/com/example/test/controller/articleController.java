package com.example.test.controller;

import com.example.test.dto.articleForm;
import com.example.test.entity.Article;
import com.example.test.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j  //로깅을 위한 어노테이션
public class articleController {
    @Autowired  //자동연결
    private ArticleRepository articleRepository;  //원래는 new를 사용해서 객체 생성해야하는데 Autowired를 통해서 가능
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "/articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(articleForm form){
        log.info(form.toString());
        
        //1. DTO를 Entity로 변환
        Article article = form.toEntity();
        log.info(article.toString());
        //2. Repository에게 Entity를 DB안에 저장하게 함
        Article saved = articleRepository.save(article);  //저장메서드
        log.info(saved.toString());
        return "redirect:/articles/"+saved.getId();
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id,Model model){ //파라미터로 받는 값
        log.info("id="+id);
        //1. id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //2. 가져온 데이터를 모델에 등록
        model.addAttribute("article",articleEntity);
        //3.  보여줄 페이지를 설정

        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 Article을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();
        //2. 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList",articleEntityList);
        //3. 뷰 페이지 설정
        return "articles/index";
    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id,Model model){
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록
        model.addAttribute("article",articleEntity);

        //뷰 페이지 설정

        return "articles/edit";
    }



}
