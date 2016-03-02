package com.lthd.beesite.domain.feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FeedController {
	@Autowired
	private DocumentRssFeedView feedView;
	
	@RequestMapping(value="/feed.*", produces = "application/*", method=RequestMethod.GET)
	public DocumentRssFeedView getContent() {
//		feedView.addStaticAttribute("feeds", new MyFeed().createFeed());
		
		return feedView;
	}
}
