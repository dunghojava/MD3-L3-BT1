package com.codegym.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration // đánh dấu đây là file cấu hình dự án Spring
@EnableWebMvc // đánh dấu dự án này hỗ trợ mô hình MVC
@ComponentScan("com.codegym.controller")
public class AppConfiguration  implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext; // khai báo 1 Spring Container

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //Cấu hình Thymeleaf
    @Bean
    public SpringResourceTemplateResolver templateResover() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views"); //tiền tố
        templateResolver.setSuffix(".html"); //hậu tố
        templateResolver.setTemplateMode(TemplateMode.HTML); //kiểu views
        templateResolver.setCharacterEncoding("UTF-8"); //định dạng chữ
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResover());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8"); //định dạng chữ
        return viewResolver;
    }
}
