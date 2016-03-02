package com.lthd.beesite.domain.service.comment;

import org.springframework.stereotype.Service;

import com.lthd.beesite.app.dto.CommentDto;
import com.lthd.beesite.domain.entity.Comment;
import com.lthd.beesite.domain.service.generic.GenericServiceImpl;

@Service
public class CommentServiceImpl extends GenericServiceImpl<Comment, CommentDto, Integer> implements CommentService {

}
