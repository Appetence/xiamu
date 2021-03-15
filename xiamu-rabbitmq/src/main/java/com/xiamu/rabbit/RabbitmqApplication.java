package com.xiamu.rabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author : xiamu
 */
@SpringBootApplication
public class RabbitmqApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RabbitmqApplication.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(RabbitmqApplication.class, args);
    }
}