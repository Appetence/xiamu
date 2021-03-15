package com.umpay.rms.gpd.user.config.redis;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: rms-gpd
 * @description: redison配置
 * @author: xiamu
 * @create: 2020-08-19 16:07
 */
@Configuration
public class RedisonConfig {
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private String port;


    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        //netty io model  ,io采用的是传统的select 轮询模式，效率较低批；kqueue epoll采用callback()模式  字节数据较多的时候，活跃的socket会主动调用callback()函数,完成相关操作，避免传统select的轮询
        config.setTransportMode(TransportMode.NIO);
        //config.useClusterServers().addNodeAddress("127.0.0.1:6379");
        config.useSingleServer().setAddress("redis://"+redisHost+":"+port);
        return Redisson.create(config);
    }
}