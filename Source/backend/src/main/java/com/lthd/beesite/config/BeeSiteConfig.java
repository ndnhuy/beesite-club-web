package com.lthd.beesite.config;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.sync.diffsync.web.JsonPatchHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class BeeSiteConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private OnlineStoreInterceptor onlineStoreInterceptor;
	
	@Bean
	public DozerBeanMapper dozerBeanMapper() {
		return new DozerBeanMapper();
	}
	
	@Bean
	public JsonPatchHttpMessageConverter jsonPatchMessageConverter() {
		return new JsonPatchHttpMessageConverter();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		
		registry.addInterceptor(onlineStoreInterceptor);
	}
}
