package com.lthd.beesite.app.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lthd.beesite.app.controller.common.utils.Constant;
import com.lthd.beesite.app.dto.RestSuccess;
import com.lthd.beesite.domain.service.user.UserService;

@RestController
@RequestMapping(value="/users")
@Secured(Constant.ROLE_ADMIN)
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public RestSuccess getAll() {
		return new RestSuccess(HttpStatus.OK.value(), userService.findAll(), null);
	}
	
}
