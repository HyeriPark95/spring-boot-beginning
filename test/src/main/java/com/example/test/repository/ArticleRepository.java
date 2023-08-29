package com.example.test.repository;

import com.example.test.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article,Long> {  //관리대상,id의 타입
    //부모의 메서드를 그대로 가져와서 사용할 것임
    //JPA가 알아서 구현체 클래스를 생성해줄 것임
}
