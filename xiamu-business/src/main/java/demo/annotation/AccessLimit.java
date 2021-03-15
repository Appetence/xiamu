package demo.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @program: vue2x
 * @description: 接口恶意访问限制
 * @Author: xiamu
 * @create: 2021-02-01 14:01
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {
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
     * 是否需要登陆
     * @return
     */
    boolean needLogin()default true;
}
