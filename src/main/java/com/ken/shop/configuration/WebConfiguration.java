package com.ken.shop.configuration;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.h2.server.web.WebServlet;

/**
 * Created by adeyinkaokuneye on 23/11/2015.
 */
@Configuration
public class WebConfiguration {

    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
}