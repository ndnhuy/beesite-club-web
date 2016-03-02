package com.lthd.beesite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lthd.beesite.domain.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	List<Article> findByContentContainingIgnoreCase(String contentQuery);
	List<Article> findByTitleContainingIgnoreCase(String titleQuery);
	List<Article> findByTagContainingIgnoreCase(String tag);
}
