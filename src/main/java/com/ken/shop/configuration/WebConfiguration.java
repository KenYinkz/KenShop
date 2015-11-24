package com.ken.shop.configuration;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.h2.server.web.WebServlet;

/**
 * Created by adeyinkaokuneye on 23/11/2015.
 */
@Configuration
public class WebConfiguration {

    /** we can move this to an admin endpoint later on */
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

    /**
     * Add caching abilities...set cache manager
     * @return
     */
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("items", "guests", "store");
    }

}