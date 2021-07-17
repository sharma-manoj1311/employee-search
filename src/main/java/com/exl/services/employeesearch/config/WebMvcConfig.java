package com.exl.services.employeesearch.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/");	
    }
	
	@Bean
	  public TilesConfigurer tilesConfigurer() {
	    TilesConfigurer tilesConfigurer = new TilesConfigurer();
	    tilesConfigurer.setDefinitions(
	        "/WEB-INF/tiles/tiles-resolver.xml");
	    tilesConfigurer.setCheckRefresh(true);
	    return tilesConfigurer;
	  }

	  @Bean
	  public TilesViewResolver tilesViewResolver() {
	    final TilesViewResolver resolver = new TilesViewResolver();
	    resolver.setViewClass(TilesView.class);
	    resolver.setOrder(0);
	    return resolver;
	  }
	  
	
		
}