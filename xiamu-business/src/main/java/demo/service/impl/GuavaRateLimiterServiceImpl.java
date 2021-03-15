package demo.service.impl;

import com.google.common.util.concurrent.RateLimiter;
import demo.service.GuavaRateLimiterService;
import org.springframework.stereotype.Service;

/**
 * @program: vue2x
 * @description:
 * @Author: xiamu
 * @create: 2021-03-01 16:15
 */
@Service
public class GuavaRateLimiterServiceImpl implements GuavaRateLimiterService {
    /**
     * 每秒控制5个许可
     */
    RateLimiter rateLimiter = RateLimiter.create(5.0);

    /**
     * 获取令牌
     *
     * @return
     */
    @Override
    public boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }

}


