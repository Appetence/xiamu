package demo.config.interceptor;

import demo.annotation.AccessLimit;
import demo.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: vue2x
 * @description: 请求限制
 * @Author: xiamu
 * @create: 2021-02-01 14:12
 */
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断请求是否属于方法的请求
        if (handler instanceof HandlerMethod) {

            HandlerMethod hm = (HandlerMethod) handler;

            //获取方法中的注解,看是否有该注解
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean login = accessLimit.needLogin();
            if(login){
                /**
                 * 对登陆的一系列判断
                 */

            }
            String key = request.getRequestURI();
            String stringCache = redisUtil.get(key).toString();
            if (StringUtils.isEmpty(stringCache)) {
                redisUtil.set(key, "1", seconds);
            } else {
                if(Integer.parseInt(stringCache) >= maxCount){
                    return false;
                }
                //redis需要多做一次io请求，不太适用于响应较敏捷的环境，但是适用于分布式环境
                redisUtil.set(key, String.valueOf(Integer.parseInt(stringCache) + 1), seconds);
            }

        }
        return true;

    }
    /*
    private void render(HttpServletResponse response, CodeMsg cm)throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str  = JSON.toJSONString(Result.error(cm));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }*/

}
