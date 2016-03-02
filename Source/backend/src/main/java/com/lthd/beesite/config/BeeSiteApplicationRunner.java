package com.lthd.beesite.config;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.lthd.beesite.app.dto.CommentDto;
import com.lthd.beesite.domain.entity.Comment;
import com.lthd.beesite.repository.CommentRepository;

@ComponentScan("com.lthd.beesite")
@EnableJpaRepositories(basePackages="com.lthd.beesite.repository")
@EntityScan("com.lthd.beesite.domain.entity")
@EnableAutoConfiguration
@SpringBootApplication
public class BeeSiteApplicationRunner extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(BeeSiteApplicationRunner.class);
	}
	
	public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BeeSiteApplicationRunner.class, args);
        
//        CommentRepository repo = ctx.getBean(CommentRepository.class);
//        DozerBeanMapper mapper = ctx.getBean(DozerBeanMapper.class);
//
//        
//        Comment comment = repo.findOne(1);
//        CommentDto dto = mapper.map(comment, CommentDto.class);
//        
//        System.out.println(dto.getUsername() + " " + dto.getUserId());
    }
}
