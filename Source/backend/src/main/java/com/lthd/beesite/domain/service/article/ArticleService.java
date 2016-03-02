package com.lthd.beesite.domain.service.article;

import java.util.List;

import com.lthd.beesite.app.dto.ArticleDto;
import com.lthd.beesite.domain.entity.Article;
import com.lthd.beesite.domain.service.generic.GenericService;

public interface ArticleService extends GenericService<Article, ArticleDto, Integer> {
	List<ArticleDto> findByContentLike(String contentQuery);
	List<ArticleDto> findByTitleLike(String titleQuery);
	List<ArticleDto> findByTag(String tag);
		
}
