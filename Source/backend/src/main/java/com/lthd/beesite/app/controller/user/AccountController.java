package com.lthd.beesite.app.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lthd.beesite.app.controller.common.utils.AuthenticationFacade;
import com.lthd.beesite.app.controller.common.utils.Constant;
import com.lthd.beesite.app.dto.MemberDto;
import com.lthd.beesite.app.dto.RegistrationInfoDto;
import com.lthd.beesite.app.dto.RestSuccess;
import com.lthd.beesite.app.dto.UserDto;
import com.lthd.beesite.domain.service.exception.EntityNotFoundException;
import com.lthd.beesite.domain.service.member.MemberService;
import com.lthd.beesite.domain.service.user.UserService;

@RestController
@RequestMapping(value="/account")
public class AccountController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	AuthenticationFacade auth;
	
	@Secured({Constant.ROLE_USER, Constant.ROLE_ADMIN})
	@RequestMapping(method=RequestMethod.GET)
	public RestSuccess getAccount() {
		
		UserDto userDto = auth.getCurrentLoggedInUser();
		
		Map<String, String> returnData = new HashMap<String, String>();
		
		MemberDto memberDto = null;
		try {
			memberDto = memberService.findMemberInfoOfUser(userDto.getId());
			returnData.put("name", memberDto.getName());
		} catch (EntityNotFoundException e) {
			
		}
		
		
		returnData.put("username", userDto.getUsername());
		returnData.put("id", userDto.getId().toString());
		
		return new RestSuccess(HttpStatus.OK.value(), 
				returnData
				, null);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public RestSuccess register(@RequestBody RegistrationInfoDto regisInfo) {
		
		return new RestSuccess(HttpStatus.CREATED.value(), userService.registerNewUser(regisInfo), "Your account is created");
	}
}
