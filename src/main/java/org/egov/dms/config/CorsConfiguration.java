package org.egov.dms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfiguration implements WebMvcConfigurer{

	
	public void adCorsConfig(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedHeaders("Content-Type, Accept, X-Requested-With, remember-me")
			.allowedMethods("GET", "POST", "PUT")
			.allowCredentials(true)
			.maxAge(3600);
	}
	
}
