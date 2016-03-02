package com.lthd.beesite.domain.service.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lthd.beesite.app.dto.MemberDto;
import com.lthd.beesite.domain.entity.Member;
import com.lthd.beesite.domain.service.exception.EntityNotFoundException;
import com.lthd.beesite.domain.service.generic.GenericServiceImpl;
import com.lthd.beesite.repository.MemberRepository;

/**
 * @author Huy Nguyen
 */
@Service
public class MemberServiceImpl extends GenericServiceImpl<Member, MemberDto, Integer> implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public MemberDto findMemberInfoOfUser(Integer userId) {
		Member member = memberRepository.findByUserId(userId);
		if (member == null) {
			throw new EntityNotFoundException("Cannot find userId = " + userId, null);
		}
		return mapper.map(member, MemberDto.class);
	}

}
