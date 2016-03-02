package com.lthd.beesite.app.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lthd.beesite.domain.entity.Article;
import com.lthd.beesite.domain.entity.Comment;
import com.lthd.beesite.domain.entity.User;
import com.lthd.beesite.repository.ArticleRepository;

@RestController
@RequestMapping(value="/")
public class HomeController {
	
	@Autowired
	private ArticleRepository repo;
	
	@RequestMapping(method=RequestMethod.GET)
	public String home() {
		
		Article a = repo.findOne(1);
        
        Comment c = new Comment();
        
        User user = new User();
        user.setId(2);
        
        c.setUser(user);
        c.setArticle(a);
        
        a.getComments().add(c);
        
        repo.save(a);
		
		return "WELCOME TO BEE SITE SERVICE";
	}
}
