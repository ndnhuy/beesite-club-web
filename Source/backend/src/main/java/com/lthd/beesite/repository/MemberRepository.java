package com.lthd.beesite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lthd.beesite.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	
	@Query("select m from Member m where m.user.id = :userId")
	Member findByUserId(@Param("userId") Integer userId);
}
