package com.annoying.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8081")
						.allowedMethods("GET", "POST", "HEAD", "OPTIONS", "PUT", "PATCH", "DELETE")
						.allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
								"Access-Control-Request-Headers")
						.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials").allowCredentials(true)
						.maxAge(3600);
			}
		};
	}

}
