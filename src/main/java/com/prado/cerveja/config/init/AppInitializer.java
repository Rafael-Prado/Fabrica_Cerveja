package com.prado.cerveja.config.init;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


import com.prado.cerveja.config.JPAConfig;
import com.prado.cerveja.config.SecurityConfig;
import com.prado.cerveja.config.ServiceConfig;
import com.prado.cerveja.config.WebConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{ JPAConfig.class, ServiceConfig.class , SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	protected Filter[] getServletFilter() {
		return new Filter[] {};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
		


}
