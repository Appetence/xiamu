package demo.config.advice;

import demo.annotation.SerializedField;
import org.springframework.core.MethodParameter;

/**
 * @program: vue2x
 * @description:
 * @Author: xiamu
 * @create: 2021-02-01 20:17
 */
public abstract class AbstractEncodeAdvice {

    public boolean isEncode(MethodParameter returnType) {
        boolean isEncode = false;
        if (returnType.getMethod().isAnnotationPresent(SerializedField.class)) {
            //获取注解配置的包含和去除字段
            SerializedField serializedField = returnType.getMethodAnnotation(SerializedField.class);
            //是否加密
            isEncode = serializedField.encode();
        }
        return isEncode;
    }
}
