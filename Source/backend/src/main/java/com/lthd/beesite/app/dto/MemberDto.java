package com.lthd.beesite.app.dto;

import org.dozer.Mapping;

public class MemberDto {
	
	@Mapping("id")
	Integer id;
	
	@Mapping("name")
	String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
