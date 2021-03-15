package demo.util.limitRate;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @program: vue2x
 * @description: 限流
 * @Author: xiamu
 * @create: 2021-03-01 15:34
 */
public class GuavaLimitRateUtil {

    public static final Integer limit = new Integer("1");

    public static boolean limitRate(String rate){
        RateLimiter rateLimiter = RateLimiter.create(10);
        return false;

    }

}
