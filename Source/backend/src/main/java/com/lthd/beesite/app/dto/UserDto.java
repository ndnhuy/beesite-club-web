package com.lthd.beesite.app.dto;

import org.dozer.Mapping;

public class UserDto {
	@Mapping("id")
	private Integer id;
	
	@Mapping("username")
	private String username;
	
	@Mapping("password")
	private String password;
	
	@Mapping("email")
	private String email;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
