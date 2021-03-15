package com.xiamu.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @program: xiamu
 * @description:
 * @author: xiamu
 * @create: 2021-03-03 19:31
 */
@SpringBootApplication
public class OAuth2Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OAuth2Application.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(OAuth2Application.class, args);
    }
}
