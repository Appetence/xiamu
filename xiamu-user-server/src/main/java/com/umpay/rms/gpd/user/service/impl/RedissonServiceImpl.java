package com.umpay.rms.gpd.user.service.impl;

/**
 * @program: rms-gpd
 * @description: redisson service
 * @author: xiamu
 * @create: 2020-08-19 18:24
 */

import com.umpay.rms.gpd.user.service.RedissonService;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RedissonServiceImpl implements RedissonService {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void getRedissonClient() throws IOException {
        Config config = redissonClient.getConfig();
        System.out.println(config.toJSON().toString());
    }

    /**
     * `
     * 25      * 获取字符串对象
     * 26      *
     * 27      * @param objectName
     * 28      * @return
     * 29
     */
    @Override

    public <T> RBucket<T> getRBucket(String objectName) {
        RBucket<T> bucket = redissonClient.getBucket(objectName);
        return bucket;
    }

    /**
     * 36      * 获取Map对象
     * 37      *
     * 38      * @param objectName
     * 39      * @return
     * 40
     */
    @Override

    public <K, V> RMap<K, V> getRMap(String objectName) {
        RMap<K, V> map = redissonClient.getMap(objectName);
        return map;
    }

    /**
     * 47      * 获取有序集合
     * 48      *
     * 49      * @param objectName
     * 50      * @return
     * 51
     */
    @Override

    public <V> RSortedSet<V> getRSortedSet(String objectName) {
        RSortedSet<V> sortedSet = redissonClient.getSortedSet(objectName);
        return sortedSet;
    }

    /**
     * 58      * 获取集合
     * 59      *
     * 60      * @param objectName
     * 61      * @return
     * 62
     */
    @Override

    public <V> RSet<V> getRSet(String objectName) {
        RSet<V> rSet = redissonClient.getSet(objectName);
        return rSet;
    }

    /**
     * 69      * 获取列表
     * 70      *
     * 71      * @param objectName
     * 72      * @return
     * 73
     */
    @Override

    public <V> RList<V> getRList(String objectName) {
        RList<V> rList = redissonClient.getList(objectName);
        return rList;
    }

    /**
     * 80      * 获取队列
     * 81      *
     * 82      * @param objectName
     * 83      * @return
     * 84
     */
    @Override

    public <V> RQueue<V> getRQueue(String objectName) {
        RQueue<V> rQueue = redissonClient.getQueue(objectName);
        return rQueue;
    }

    /**
     * 91      * 获取双端队列
     * 92      *
     * 93      * @param objectName
     * 94      * @return
     * 95
     */
    @Override

    public <V> RDeque<V> getRDeque(String objectName) {
        RDeque<V> rDeque = redissonClient.getDeque(objectName);
        return rDeque;
    }

    /**
     * 103      * 获取锁
     * 104      *
     * 105      * @param objectName
     * 106      * @return
     * 107
     */
    @Override

    public RLock getRLock(String objectName) {
        RLock rLock = redissonClient.getLock(objectName);
        return rLock;
    }

    /**
     * 114      * 获取读取锁
     * 115      *
     * 116      * @param objectName
     * 117      * @return
     * 118
     */
    @Override

    public RReadWriteLock getRWLock(String objectName) {
        RReadWriteLock rwlock = redissonClient.getReadWriteLock(objectName);
        return rwlock;
    }

    /**
     * 125      * 获取原子数
     * 126      *
     * 127      * @param objectName
     * 128      * @return
     * 129
     */
    @Override

    public RAtomicLong getRAtomicLong(String objectName) {
        RAtomicLong rAtomicLong = redissonClient.getAtomicLong(objectName);
        return rAtomicLong;
    }

    /**
     * 136      * 获取记数锁
     * 137      *
     * 138      * @param objectName
     * 139      * @return
     * 140
     */
    @Override

    public RCountDownLatch getRCountDownLatch(String objectName) {
        RCountDownLatch rCountDownLatch = redissonClient.getCountDownLatch(objectName);
        return rCountDownLatch;
    }

    /**
     * 147      * 获取消息的Topic
     * 148      *
     * 149      * @param objectName
     * 150      * @return
     * 151
     */
/*    public <M> RTopic<M> getRTopic(String objectName) {
        RTopic<M> rTopic = redissonClient.getTopic(objectName);
        return rTopic;
    }*/
}
