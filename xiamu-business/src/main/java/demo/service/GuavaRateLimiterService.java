package demo.service;

/**
 * @program: vue2x
 * @description: 限流
 * @Author: xiamu
 * @create: 2021-03-01 16:14
 */
public interface GuavaRateLimiterService {

    public boolean tryAcquire();
}
