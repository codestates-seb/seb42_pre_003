package com.jmc.stackoverflowbe.article.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private Long answers;

    @Column
    private Long views;

    @Builder
    public Article(Long id, String title, Long answers, Long views) {
        this.id = id;
        this.title = title;
        this.answers = answers;
        this.views = views;
    }
}
