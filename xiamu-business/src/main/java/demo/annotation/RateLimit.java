package demo.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * @program: vue2x
 * @description: 接口限流，响应效率较高的青口
 * @Author: xiamu
 * @create: 2021-03-01 16:46
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface RateLimit {

    /**
     * 自定义的属性，可以通过配置参数进行自定义操作
     * @return
     */
    int seconds();

    /**
     * 每秒最大次数
     * @return
     */
    int maxCount();

    /**
     * 类型
     * @return
     */
    String type();

}



