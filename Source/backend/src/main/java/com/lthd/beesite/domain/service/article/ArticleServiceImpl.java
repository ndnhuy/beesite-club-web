package com.lthd.beesite.domain.service.article;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lthd.beesite.app.dto.ArticleDto;
import com.lthd.beesite.app.dto.CommentDto;
import com.lthd.beesite.domain.entity.Article;
import com.lthd.beesite.domain.entity.Comment;
import com.lthd.beesite.domain.service.comment.CommentService;
import com.lthd.beesite.domain.service.exception.EntityNotFoundException;
import com.lthd.beesite.domain.service.generic.GenericServiceImpl;
import com.lthd.beesite.repository.ArticleRepository;


@Service
public class ArticleServiceImpl extends GenericServiceImpl<Article, ArticleDto, Integer> implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentService commentService;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<ArticleDto> findByContentLike(String contentQuery) {
		
		List<Article> articles = articleRepository.findByContentContainingIgnoreCase(contentQuery);
		
		List<ArticleDto> dtoArticles = new ArrayList<ArticleDto>();
		for (Article a : articles) {
			dtoArticles.add(mapper.map(a, ArticleDto.class));
		}
		
		return dtoArticles;
	}

	@Override
	public List<ArticleDto> findByTitleLike(String titleQuery) {
		
		List<Article> articles = articleRepository.findByTitleContainingIgnoreCase(titleQuery);
		
		List<ArticleDto> dtoArticles = new ArrayList<ArticleDto>();
		for (Article a : articles) {
			dtoArticles.add(mapper.map(a, ArticleDto.class));
		}
		
		return dtoArticles;

	}

	@Override
	public List<ArticleDto> findByTag(String tag) {
		
		List<Article> articles = articleRepository.findByTagContainingIgnoreCase(tag);
		
		List<ArticleDto> dtoArticles = new ArrayList<ArticleDto>();
		for (Article a : articles) {
			dtoArticles.add(mapper.map(a, ArticleDto.class));
		}
		
		return dtoArticles;

	}
	
	@Override
	public void delete(Integer id) {
		Article article = articleRepository.findOne(id);
		if (article == null) {
			throw new EntityNotFoundException(null, "Cannot find article id " + id);
		}
		
		for (Comment comment : article.getComments()) {
			commentService.delete(comment.getId());
		}
		
		
		super.delete(id);
	}

}
