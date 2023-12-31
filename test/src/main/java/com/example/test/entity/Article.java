package com.example.test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.processing.Generated;

@Entity   //db가 해당 객체를 인식 (이 이름으로 테이블을 만든다)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@SequenceGenerator(
        name = "id_generator",
        sequenceName = "BOARD_SEQ",
        initialValue = 4,
        allocationSize = 1
)
public class Article {  //이 필드를 바탕으로 데이터베이스 생성

    @Id  //대표값을 지정
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "id_generator")  //시퀀스처럼 번호 자동 생성 1,2,3,...
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        if(article.title!=null)
            this.title = article.title;
        if(article.content!=null)
            this.content = article.content;
    }
}
