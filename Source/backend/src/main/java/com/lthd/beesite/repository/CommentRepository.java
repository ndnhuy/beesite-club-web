package com.lthd.beesite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lthd.beesite.domain.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
