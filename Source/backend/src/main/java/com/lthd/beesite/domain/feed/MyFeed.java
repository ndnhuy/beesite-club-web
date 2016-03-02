package com.lthd.beesite.domain.feed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyFeed {
	private String id;
    private String title;
    private String description;
    private Date pubDate;
    private String link;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<MyFeed> createFeed() {
		List<MyFeed> feeds = new ArrayList<>();
		
		MyFeed feed1 = new MyFeed();
		feed1.setId("100");
		feed1.setTitle("Title 1");
		feed1.setDescription("Desceiption one");
		feed1.setLink("https://www.google.com/?gws_rd=ssl");
		feed1.setPubDate(new Date());
		feeds.add(feed1);
		
		MyFeed feed2 = new MyFeed();
		feed2.setId("200");
		feed2.setTitle("Title 2");
		feed2.setDescription("Desceiption one");
		feed2.setLink("https://www.google.com/?gws_rd=ssl");
		feed2.setPubDate(new Date());
		feeds.add(feed2);
		
		return feeds;
	}
    
}	
