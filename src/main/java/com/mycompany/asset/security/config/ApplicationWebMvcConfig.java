package com.mycompany.asset.security.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
@EnableWebMvc
public class ApplicationWebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
	

	@PostConstruct
	public void init() {
		requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		System.out.println(" Application WEB MVC Config");
		registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multiPartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		return resolver;
	}



}
