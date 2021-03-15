package demo.annotation;

import java.lang.annotation.*;

/**
 * @author : xiamu
 * @date 2018/2/5 17:32
 * @desc
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SignVerify {
}
