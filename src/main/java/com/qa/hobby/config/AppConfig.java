package com.qa.hobby.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {
	
	
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	
}
