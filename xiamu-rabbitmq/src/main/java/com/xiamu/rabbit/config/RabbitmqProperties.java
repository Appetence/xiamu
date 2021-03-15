package com.xiamu.rabbit.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @program: vue2x
 * @description:
 * @Author: xiamu
 * @create: 2021-02-18 17:33
 */
@Data
@Service
public class RabbitmqProperties {

    @Value("${spring.rabbit.address}")
    public String address;
    @Value("${spring.rabbit.username}")
    public String username;
    @Value("${spring.rabbit.password}")
    public String password;
    @Value("${spring.rabbit.virtualHost}")
    public String mqRabbitVirtualHost;
    @Value("${spring.rabbit.exchange.name}")
    public String exchangeName;
    @Value("${spring.rabbit.size}")
    public int queueSize;
    @Value("${spring.rabbit.concurrent.consumers}")
    public int concurrentConsumers;
    @Value("${spring.rabbit.prefetch.count}")
    public int prefetchCount;
    @Value("${spring.rabbit.queues.url}")
    public String queuesUrl;

}