package com.lthd.beesite.domain.feed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.lthd.beesite.app.dto.ArticleDto;
import com.lthd.beesite.domain.service.article.ArticleService;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Content;
import com.rometools.rome.feed.rss.Item;

@Component("documentRssFeedView")
public class DocumentRssFeedView extends AbstractRssFeedView {

	
    private final ArticleService articleService;

    @Value("${base-url}")
    private String baseUrl;

    @Autowired
    public DocumentRssFeedView(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    protected Channel newFeed() {
        Channel channel = new Channel("rss_2.0");
        channel.setLink(baseUrl + "feed.xml");
        channel.setTitle("BEE Club");
        channel.setDescription("BEE Club is the official english club of HCMUS.");
        
        return channel;
    }

    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model,
                                        HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse) throws Exception {
        List<Item> items = new ArrayList<>();
        
      
        for (ArticleDto article : articleService.findAll()) {
          	Item item = new Item();
          	
          	item.setTitle(article.getTitle());
          	item.setLink(baseUrl + "#/articles/" + article.getId());
          	item.setPubDate(new Date());
          	
          	Content content = new Content();
          	content.setValue(article.getContent());
          	
          	item.setContent(content);
          	
          	items.add(item);
        }
//        Object ob = model.get("feeds");
//        if (ob instanceof List) {
//        	for (int i = 0; i < ((List<?>)ob).size(); i++) {
//        		Object feedObj = ((List<?>) ob).get(i);
//        		MyFeed myFeed = (MyFeed) feedObj;
//        		Item item = new Item();
//        		item.setTitle(myFeed.getTitle());
//        		item.setLink(myFeed.getLink());
//        		item.setPubDate(myFeed.getPubDate());
//        		Content content = new Content();
//        		content.setValue(myFeed.getDescription());
//        		item.setContent(content);
//        		
//        		items.add(item);
//        	}
//        }
        return items;
    }


}
