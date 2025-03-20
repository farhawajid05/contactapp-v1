package com.stackroute.contactapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    public static final String API_PATTERN = "/api/v1/**";

    @Bean
    public Docket apiDocumentation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant(API_PATTERN))
                .apis(RequestHandlerSelectors.basePackage("com.stackroute"))
                .build()
                .apiInfo(new ApiInfo(
                        "CONTACT API DOCUMENTATION",
                        "APi for storing contacts",
                        "1.0",
                        "http://www.google.com",
                        new Contact("Ghouse", "url", "myemail"),
                        "license",
                        "url",
                        List.of()
                ));

    }
}
