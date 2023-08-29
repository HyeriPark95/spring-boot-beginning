package com.example.test.repository;

import com.example.test.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article,Long> {  //관리대상,id의 타입
    //부모의 메서드를 그대로 가져와서 사용할 것임
    //JPA가 알아서 구현체 클래스를 생성해줄 것임


    @Override
    ArrayList<Article> findAll();  //오버라이드 해줌 List타입으로 반환할 수 있도록
}
