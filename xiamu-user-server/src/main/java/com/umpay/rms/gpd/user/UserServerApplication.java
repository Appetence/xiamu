package com.umpay.rms.gpd.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: rms-gpd
 * @description:
 * @author: xiamu
 * @create: 2020-08-21 14:10
// */
//@EnableFeignClients
//@EnableCircuitBreaker
@EnableScheduling
//@EnableDiscoveryClient
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.umpay.rms.gpd", "com.umpay.rms.gpd.user.security", "com.umpay.rms.gpd.user.config","com.umpay.rms.gpd.mq"})
public class UserServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }
}
