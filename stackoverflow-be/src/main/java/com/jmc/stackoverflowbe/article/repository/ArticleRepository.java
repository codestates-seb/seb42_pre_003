package com.jmc.stackoverflowbe.article.repository;

import com.jmc.stackoverflowbe.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
