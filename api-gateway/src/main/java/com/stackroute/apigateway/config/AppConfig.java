package com.stackroute.apigateway.config;

import com.stackroute.apigateway.filter.JWTValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

@Configuration
    public class AppConfig {

        public static final String CONTACTS_PATH = "/contact-service/api/v1/contacts/*";

        @Bean
        public FilterRegistrationBean<GenericFilterBean> jwtFilter(){
            FilterRegistrationBean<GenericFilterBean> filterRegistrationBean = new FilterRegistrationBean<>();
            filterRegistrationBean.setFilter(new JWTValidationFilter());
            filterRegistrationBean.addUrlPatterns(CONTACTS_PATH);
            return filterRegistrationBean;
        }


}
