package com.example.test.api;

import com.example.test.dto.articleForm;
import com.example.test.entity.Article;
import com.example.test.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ArticleAPIContoller {
    @Autowired  //DI
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody articleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }
    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@RequestBody articleForm dto, @PathVariable Long id){
        //1. 수정용 Entity생성 (받은 수정용 데이터)
        Article article = dto.toEntity();
        //2. 대상 엔티티 조회 (id로 찾은 db내의 데이터)
        Article target = articleRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리 (대상이 없거나, id가 다른경우)
        if(target == null || id != article.getId()){
            // 400,잘못된 요청 응답
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); //body엔 아무런 값도 없음
        }
        //4. 업데이트 및 정상응답
        log.info("target:"+target);
        target.patch(article);  //기존에 있던 것에 새로운 것을 붙임
        Article updated =articleRepository.save(target); //붙인 것을 저장
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if(target==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 대상 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null);

    }
}
