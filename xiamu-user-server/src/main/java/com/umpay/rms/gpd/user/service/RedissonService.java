package com.umpay.rms.gpd.user.service;

/**
 * @program: rms-gpd
 * @description: redisson service
 * @author: xiamu
 * @create: 2020-08-19 18:24
 */

import org.redisson.api.*;

import java.io.IOException;

public interface RedissonService {



    public void getRedissonClient()  throws IOException;

    /**
     * `
     * 25      * 获取字符串对象
     * 26      *
     * 27      * @param objectName
     * 28      * @return
     * 29
     */
    public <T> RBucket<T> getRBucket(String objectName);

    /**
     * 36      * 获取Map对象
     * 37      *
     * 38      * @param objectName
     * 39      * @return
     * 40
     */
    public <K, V> RMap<K, V> getRMap(String objectName);

    /**
     * 47      * 获取有序集合
     * 48      *
     * 49      * @param objectName
     * 50      * @return
     * 51
     */
    public <V> RSortedSet<V> getRSortedSet(String objectName);
    /**
     * 58      * 获取集合
     * 59      *
     * 60      * @param objectName
     * 61      * @return
     * 62
     */
    public <V> RSet<V> getRSet(String objectName);

    /**
     * 69      * 获取列表
     * 70      *
     * 71      * @param objectName
     * 72      * @return
     * 73
     */
    public <V> RList<V> getRList(String objectName);
    /**
     * 80      * 获取队列
     * 81      *
     * 82      * @param objectName
     * 83      * @return
     * 84
     */
    public <V> RQueue<V> getRQueue(String objectName) ;

    /**
     * 91      * 获取双端队列
     * 92      *
     * 93      * @param objectName
     * 94      * @return
     * 95
     */
    public <V> RDeque<V> getRDeque(String objectName) ;

    /**
     * 103      * 获取锁
     * 104      *
     * 105      * @param objectName
     * 106      * @return
     * 107
     */
    public RLock getRLock(String objectName);
    /**
     * 114      * 获取读取锁
     * 115      *
     * 116      * @param objectName
     * 117      * @return
     * 118
     */
    public RReadWriteLock getRWLock(String objectName) ;

    /**
     * 125      * 获取原子数
     * 126      *
     * 127      * @param objectName
     * 128      * @return
     * 129
     */
    public RAtomicLong getRAtomicLong(String objectName) ;
    /**
     * 136      * 获取记数锁
     * 137      *
     * 138      * @param objectName
     * 139      * @return
     * 140
     */
    public RCountDownLatch getRCountDownLatch(String objectName);

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
