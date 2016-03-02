package com.lthd.beesite.app.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lthd.beesite.app.dto.RestSuccess;
import com.lthd.beesite.domain.service.member.MemberService;

@RestController
@RequestMapping(value="/members")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(method=RequestMethod.GET)
	public RestSuccess getAll() {
		return new RestSuccess(HttpStatus.OK.value(), memberService.findAll(), null);
	}
	
}
