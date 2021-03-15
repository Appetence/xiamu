package demo.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import java.lang.annotation.*;

/**
 * @program: vue2x
 * @description: 统一加解密，使用注解标识
 * @Author: xiamu
 * @create: 2021-02-01 20:07
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface SerializedField {
    /**
     * 是否加密
     *
     * @return
     */
    boolean encode() default true;
}
