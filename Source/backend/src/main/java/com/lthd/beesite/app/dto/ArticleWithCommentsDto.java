package com.lthd.beesite.app.dto;

import java.util.List;

import org.dozer.Mapping;

public class ArticleWithCommentsDto extends ArticleDto {
	
	@Mapping("comments")
	private List<CommentDto> comments;

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}
	
	
}
