package com.baeldung.resource.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author IMRAN
 *
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private UserDomainInterceptor userDomainInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userDomainInterceptor);
	}
}
