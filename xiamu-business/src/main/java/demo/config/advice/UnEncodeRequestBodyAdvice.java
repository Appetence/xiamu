package demo.config.advice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


/**
 * @program: vue2x
 * @description: 请求数据解密
 * @Author: xiamu
 * @create: 2021-02-01 17:25
 */
@ControllerAdvice(basePackages = "demo.controller")
public class UnEncodeRequestBodyAdvice extends AbstractEncodeAdvice implements RequestBodyAdvice {
    private final static Logger logger = LoggerFactory.getLogger(UnEncodeRequestBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        try {
            if (isEncode(parameter)) {
                return new CustomHttpInputMessage(inputMessage);
            }
            return inputMessage;

        } catch (Exception e) {
            e.printStackTrace();
            return inputMessage;
        }
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    class CustomHttpInputMessage implements HttpInputMessage {
        private HttpHeaders headers;

        private InputStream body;

        public CustomHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
            this.headers = inputMessage.getHeaders();
            //解密
            this.body = unEncode(inputMessage.getBody());

        }

        /**
         * 集中处理加密后的数据
         *
         * @param inputStream
         * @return
         */
        private InputStream unEncode(InputStream inputStream) {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            /**
             * 请求数据解密在这里操作
             */
            return new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8));
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }
}
