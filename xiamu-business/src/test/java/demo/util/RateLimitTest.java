package demo.util;

import demo.base.BaseTest;
import demo.service.GuavaRateLimiterService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: vue2x
 * @description:
 * @Author: xiamu
 * @create: 2021-03-01 16:17
 */
public class RateLimitTest extends BaseTest {
    @Autowired
    private GuavaRateLimiterService rateLimiterService;

    @Test
    public void testRateLimiter() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            Future<?> submit = executorService.submit(() -> {
                boolean b = rateLimiterService.tryAcquire();
                System.out.println(b);
                return b;
            });
            System.out.println(submit.get());
        }

    }
}
