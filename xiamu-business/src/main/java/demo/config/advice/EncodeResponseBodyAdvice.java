package demo.config.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * @program: vue2x
 * @description: 统一处理加解密
 * @Author: xiamu
 * @create: 2021-01-11 20:19
 */
@Component
@ControllerAdvice(basePackages = "demo.controller")
@Slf4j
public class EncodeResponseBodyAdvice extends AbstractEncodeAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (isEncode(returnType)) {
            log.info("[统一加密处理] 对方法method :" + returnType.getMethod().getName() + "返回数据进行加密");
            String encode = null;
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
                encode = result;
                //加密操作在这里处理
                //encode = XIANAPIKEY.encryptionXl(result);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("【统一加密处理】加密后结果 body={}", encode);

            return encode;
        }
        return body;
    }

}