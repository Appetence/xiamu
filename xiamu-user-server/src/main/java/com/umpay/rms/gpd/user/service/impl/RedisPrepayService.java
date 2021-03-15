package com.umpay.rms.gpd.user.service.impl;

import com.google.common.collect.Lists;
import com.umpay.rms.gpd.user.service.PrepayService;
import com.umpay.rms.gpd.user.service.RedisLuaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

/**
 * @program: rms-gpd
 * @description:
 * @author: xiamu
 * @create: 2020-10-26 23:18
 */
@Service
public class RedisPrepayService implements PrepayService {

    private final String billingLua = RedisLuaService.getInstance().regist("redisBilling.lua");

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final static String billing = "";

    @Override
    public long charge(String acctId, long amount) {
        String key = billing + acctId;
        return redisTemplate.opsForValue().increment(key, amount);
    }


    @Override
    public Long getBalance(String acctId) {
        String key = billing + acctId;
        String v = redisTemplate.opsForValue().get(key);
        return v == null ? null : Long.parseLong(v);
    }

    @Override
    public long billing(String acctId, long billAmount) {
        String key = billing + acctId;

        Long rc = redisTemplate.execute(new DefaultRedisScript<>(billingLua, Long.class), Lists.newArrayList(key), billAmount + "");
        return rc;
    }

    @Override
    public long refund(String acctId, long billAmount) {
        String key = billing + acctId;
        return redisTemplate.opsForValue().increment(key, billAmount);
    }


    @Override
    public void clear(String acctId) {
        redisTemplate.delete(billing + acctId);
    }

}
