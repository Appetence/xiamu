package com.umpay.rms.gpd.user.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @program: rms-gpd
 * @description: redis锁
 * @author: xiamu
 * @create: 2020-07-23 19:15
 */
@Component
public class RedisLock {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static Long ID = 10000000L;
    private static Long ACCOUNT_ID = 20000000L;

    public String getSerialId() {
        Long account = redisTemplate.opsForValue().increment(CommonParamsUtil.getLockKey(), 1);
        String accountKey = String.valueOf(account + ID);
        return accountKey;
    }

    public String getAccountId() {
        Long account = redisTemplate.opsForValue().increment(CommonParamsUtil.getLockKey(), 1);
        String accountKey = "acct" + (account + ACCOUNT_ID);
        return accountKey;
    }

    @Bean
    public RedisLock getRedisLock() {
        return new RedisLock();
    }


}
