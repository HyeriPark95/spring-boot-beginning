package com.example.test.service;

import com.example.test.dto.articleForm;
import com.example.test.entity.Article;
import com.example.test.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service  //서비스 선언 (서비스 객체를 스프링부트에 생성)
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(articleForm dto) {
        Article article = dto.toEntity();
        //이미 있는 번호에 생성하면 덮어씌여지기 때문에 id가 있으면 null을 반환
        if(article.getId()!=null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, articleForm dto) {
        //1. 수정용 엔티티 생성
        Article article = dto.toEntity();
        //2. 대상 엔티티 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리
        if(target==null || id != article.getId()){
            return null;
        }
        //4. 업데이트 및 정상 응답
        target.patch(article);
        return articleRepository.save(target);
    }

    public Article delete(Long id) {
        //1. 삭제할 엔티티 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //2. 잘못된 요청 처리
        if(target==null)
            return null;
        articleRepository.delete(target);
        return target;
    }
    @Transactional //해당 메서드를 트랜잭션으로 묶는다!
    public List<Article> createArticles(List<articleForm> dtos) {
        //1. dto묶음을 entity묶음으로 변환
        List<Article> articleList =  dtos.stream()
                .map(dto->dto.toEntity())
                .collect(Collectors.toList());
//        List<Article> articleList = new ArrayList<>();
//        for(int i=0;i<dtos.size();i++){
//            articleForm dto = dtos.get(i);
//            Article entity = dto.toEntity();
//            articleList.add(entity);
//        }

        //2. entity묶음을 db로 전환
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
//        for(int i=0;i<articleList.size();i++){
//            articleRepository.save(articleList.get(i));
//        }

        //3. 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                ()-> new IllegalArgumentException("결재 실패!")
        );

        //4. 결과값 반환
        return articleList;
    }
}
