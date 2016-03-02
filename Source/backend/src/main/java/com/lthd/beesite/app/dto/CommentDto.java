package com.lthd.beesite.app.dto;

import java.io.Serializable;

import org.dozer.Mapping;

public class CommentDto implements Serializable {
	
	@Mapping("user.id")
	private Integer userId;
	
	@Mapping("user.username")
	private String username;
	
	@Mapping("article.id")
	private Integer articleId;
	
	@Mapping("content")
	private String content;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
