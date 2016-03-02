package com.lthd.beesite.domain.service.member;

import com.lthd.beesite.app.dto.MemberDto;
import com.lthd.beesite.domain.entity.Member;
import com.lthd.beesite.domain.service.generic.GenericService;

public interface MemberService extends GenericService<Member, MemberDto, Integer> {
	MemberDto findMemberInfoOfUser(Integer userId);
}
