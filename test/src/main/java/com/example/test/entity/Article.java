package com.example.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.processing.Generated;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {

    @Id  //대표값을 지정
    @GeneratedValue  //시퀀스처럼 번호 자동 생성 1,2,3,...
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

}
