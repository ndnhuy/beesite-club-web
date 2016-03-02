package com.lthd.beesite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lthd.beesite.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
	User findByEmail(String email);
}
