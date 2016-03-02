package com.lthd.beesite.app.dto;

import java.io.Serializable;
import java.util.List;

import org.dozer.Mapping;

public class ArticleDto implements Serializable {
	@Mapping("id")
	private Integer id;
	
	@Mapping("title")
	private String title;
	
	@Mapping("content")
	private String content;
	
	@Mapping("image")
	private String image;
	
	@Mapping("tag")
	private String tag;
	
	@Mapping("comments")
	private List<CommentDto> comments;

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag.toLowerCase();
	}
	
	
}
