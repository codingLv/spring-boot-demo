package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.interceptor.FileUploadInterceptor;

@Configuration
public class MyConfigurer extends WebMvcConfigurerAdapter{

    @Autowired  
    private FileUploadInterceptor fileUploadInterceptor;  
    //注册拦截器  
    @Override  
    public void addInterceptors(InterceptorRegistry registry) {  
  
        registry.addInterceptor(fileUploadInterceptor);  
    }  
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/mystatic/**").addResourceLocations("classpath:/mystatic/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
