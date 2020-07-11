package com.crickbean.application;

import com.crickbean.application.util.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.crickbean.application.controllers" })
public class ServletConfig implements WebMvcConfigurer {

	// Configuration to render VIEWS
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	// Configuration to render STATIC CONTENTS (IMAGE, CSS, JS)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// Register resource handler for -

		// IMAGES
		registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/resources/assets/img/");
		registry.addResourceHandler("/images/**").addResourceLocations("file:///"+ Constants.UPLOADED_FOLDER);

		// CSS
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/assets/css/");

		// JAVASCRIPT
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/assets/js/");
		
		
		// FONT
		registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/resources/assets/fonts/");

		// BOOTSTRAP
		registry.addResourceHandler("/bootstrap/**").addResourceLocations("/WEB-INF/resources/assets/bootstrap/");

	}
	@Bean
    public CommonsMultipartResolver multipartResolver(){

        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        return resolver;
    }
}
