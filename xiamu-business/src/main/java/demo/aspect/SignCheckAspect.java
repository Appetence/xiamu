package demo.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

/**
 * 注解方式触发aop
 * 可以针对 不同接口有不同的业务需求场景下使用
 * @author : xiamu
 * @date 2020/7/6
 */
@Aspect
@Component
public class SignCheckAspect {

    /**
     * 自定义切入点
     */
    @Pointcut("@annotation(demo.annotation.SignVerify)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            JSONObject jo = JSONObject.parseObject(JSONObject.toJSONString(joinPoint.getArgs()[0]));
            TreeMap<String, String> paramMap = new TreeMap<>();
            for (Map.Entry<String, Object> entry : jo.entrySet()) {
                paramMap.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
            /**
             * 自定义操作方法
             */
        } catch (Exception e) {
        }
    }


}
