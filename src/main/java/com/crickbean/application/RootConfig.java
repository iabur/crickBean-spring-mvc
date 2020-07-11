package com.crickbean.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@ComponentScan(basePackages = {
		"com.crickbean.application.service",
		"com.crickbean.application.config.persistence",
		"com.crickbean.application.config.security",
})

public class RootConfig {
	@Bean
	GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
