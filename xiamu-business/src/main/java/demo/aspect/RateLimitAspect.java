package demo.aspect;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.RateLimiter;
import com.umpay.rms.gpd.internal.dto.ResultCode;
import com.umpay.rms.gpd.internal.dto.ResultTool;
import demo.annotation.RateLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;


/**
 * @program: vue2x
 * @description:
 * @Author: xiamu
 * @create: 2021-03-01 16:50
 */
@Component
@Scope
@Aspect
public class RateLimitAspect {

    @Resource
    private HttpServletResponse httpServletResponse;

    private static HashMap<String, RateLimiter> rateLimiterMap = new HashMap<>();


    /**
     * 切面关注这个注解，只有这个注解标注的可以进来
     */
    @Pointcut("@annotation(demo.annotation.RateLimit)")
    public void serviceLimit() {

    }

    /**
     * 获取注解配置
     *
     * @param joinPoint
     * @param rateLimit
     * @return
     */
    @Around("serviceLimit() && @annotation(rateLimit)")
    public Object around(ProceedingJoinPoint joinPoint, RateLimit rateLimit) {
        if (Objects.isNull(rateLimiterMap)) {
            getInstance(rateLimit.maxCount(), rateLimit.type());
        }
        RateLimiter rateLimiter = rateLimiterMap.get(rateLimit.type());
        Boolean flag = rateLimiter.tryAcquire();
        Object obj = null;
        try {
            if (flag) {
                obj = joinPoint.proceed();
            } else {
                String result = JSONObject.toJSONString(ResultTool.fail(ResultCode.COMMON_FAIL)).toString();
                output(httpServletResponse, result);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;
    }

    public void output(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(msg.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }

    /**
     * 代理
     *
     * @param limit
     * @return
     */
    public static synchronized void getInstance(double limit, String type) {
        if (Objects.isNull(rateLimiterMap)) {
            rateLimiterMap.put(type, buildRateLimite(limit));
        }
        /**
         * 判断是否存在key
         */
        if (Objects.isNull(rateLimiterMap.containsKey(type))) {
            rateLimiterMap.put(type, buildRateLimite(limit));
        }

    }

    private static RateLimiter buildRateLimite(double limit) {
        RateLimiter rateLimiter = RateLimiter.create(limit);
        return rateLimiter;
    }
}