package com.lthd.beesite.app.controller.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.lthd.beesite.app.dto.UserDto;
import com.lthd.beesite.domain.service.user.UserService;

@Component
public class AuthenticationFacade {
	
	@Autowired
	private UserService userService;
	
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public UserDto getCurrentLoggedInUser() {
		return userService.findByUsername(getAuthentication().getName());
	}
}
