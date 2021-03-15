package com.umpay.rms.gpd.user.security.exception;

/**
 * @program: rms-gpd
 * @description:
 * @author: xiamu
 * @create: 2020-07-04 18:29
 */

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Description 异常格式
 * @Author wwz
 * @Date 2019/07/30
 * @Param
 * @Return
 */
@JsonSerialize(using = ConsumOAuthExceptionJacksonSerializer.class)
public class ConsumOAuth2Exception extends OAuth2Exception {
    public ConsumOAuth2Exception(String msg, Throwable t) {
        super(msg, t);

    }
    public ConsumOAuth2Exception(String msg) {
        super(msg);

    }
}
