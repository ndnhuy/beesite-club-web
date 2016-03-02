package com.lthd.beesite.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;


public class RegistrationInfoDto extends UserDto {
	
	@Size(min = 0, max = 100, message = "'username' " + "{javax.validation.constraints.Size.message}")
	@NotNull
	String username;
	
	@NotNull
	String password;
	
	@Size(min = 0, max = 50, message = "'email' " + "{javax.validation.constraints.Size.message}")
	@Email
	@NotNull
	String email;
	
	@Size(min = 0, max = 30, message = "'name' " + "{javax.validation.constraints.Size.message}")
	@NotNull
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
