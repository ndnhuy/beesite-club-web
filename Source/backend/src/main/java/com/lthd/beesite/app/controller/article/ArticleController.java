package com.lthd.beesite.app.controller.article;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.sync.Patch;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lthd.beesite.app.controller.common.utils.AuthenticationFacade;
import com.lthd.beesite.app.controller.common.utils.Constant;
import com.lthd.beesite.app.dto.ArticleDto;
import com.lthd.beesite.app.dto.CommentDto;
import com.lthd.beesite.app.dto.RestSuccess;
import com.lthd.beesite.domain.service.article.ArticleService;
import com.lthd.beesite.domain.service.comment.CommentService;
import com.lthd.beesite.domain.service.exception.AppException;

@RestController
@RequestMapping("/articles")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	AuthenticationFacade auth;
	
	@RequestMapping(method=RequestMethod.GET)
	public RestSuccess getArticles() {
		return new RestSuccess(HttpStatus.OK.value(), articleService.findAll(), null);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public RestSuccess getArticleById(@PathVariable("id") Integer id) {
		return new RestSuccess(HttpStatus.OK.value(), articleService.findOne(id), null);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public RestSuccess addArticle(@RequestBody ArticleDto articleDto) {
		
		
		return new RestSuccess(HttpStatus.OK.value(), articleService.add(articleDto), null);
	}
	
	@RequestMapping(value="/{articleId}", method=RequestMethod.POST)
	public RestSuccess deleteArticle(@PathVariable("articleId") Integer articleId, @RequestParam("delete") Boolean delete) {
		articleService.delete(articleId);
		return new RestSuccess(HttpStatus.OK.value(), null, "The article [id = " + articleId + "] has been deleted.");
	}
	
	@RequestMapping(value="/{articleId}", method=RequestMethod.PATCH)
	public RestSuccess updatePartialArticle(@PathVariable("articleId") Integer id, @RequestBody Patch patch) {
		return new RestSuccess(HttpStatus.OK.value(), articleService.updateChanges(id, patch), null);
	}
	
	
	@RequestMapping(value="/{articleId}/comments", method=RequestMethod.POST)
	@Secured({Constant.ROLE_USER, Constant.ROLE_ADMIN})
	public RestSuccess postCommentOnArticle(@PathVariable("articleId") Integer articleId, @RequestParam("content") String content) {
		ArticleDto article = articleService.findOne(articleId);
		
		CommentDto dto = new CommentDto();
		dto.setUserId(auth.getCurrentLoggedInUser().getId());
		dto.setArticleId(articleId);
		dto.setContent(content);
		
		return new RestSuccess(HttpStatus.CREATED.value(), commentService.add(dto), auth.getCurrentLoggedInUser().getUsername() 
								+ " has commented on the article '" 
								+ article.getTitle() + "'");
	}

	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public RestSuccess search(@RequestParam("field") String field, @RequestParam("q") String query) {
		
		field = field.toLowerCase();
		query = query.toLowerCase();
		
		List<ArticleDto> dtoArticles = new ArrayList<ArticleDto>();
		
		switch (field) {
		case "title":
			dtoArticles = articleService.findByTitleLike(query);
			break;
		case "content":
			dtoArticles = articleService.findByContentLike(query);
			break;
		case "tag":
			if (query.equals(Constant.TAG_EVENT) || 
					query.equals(Constant.TAG_NEWS) || 
					query.equals(Constant.TAG_OTHERS)) {
				
				dtoArticles = articleService.findByTag(query);
				
			}
			else {
				throw new AppException(HttpStatus.BAD_REQUEST.value(), "There's a problem in server", "The 'q' must be " + Constant.TAG_EVENT 
						+ " or " + Constant.TAG_NEWS + " or " + Constant.TAG_OTHERS);
			}
		
			break;
		default:
			throw new AppException(HttpStatus.BAD_REQUEST.value(), "There's a problem in server", "The 'field' must be 'title' or 'content' or 'tag'");
		}
		
		
		return new RestSuccess(HttpStatus.OK.value(), dtoArticles, null);
	}
	
	
}
