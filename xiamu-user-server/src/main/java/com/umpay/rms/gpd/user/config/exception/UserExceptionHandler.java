package com.umpay.rms.gpd.user.config.exception;

/**
 * @program: xiaomu-oauth
 * @description:
 * @author: xiamu
 * @create: 2021-03-04 20:48
 */

import com.umpay.rms.gpd.internal.dto.JsonResult;
import com.umpay.rms.gpd.internal.dto.ResultCode;
import com.umpay.rms.gpd.internal.dto.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class UserExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public JsonResult<OAuth2Exception> handleOauth2(OAuth2Exception e) {
        log.warn("oauth2 认证异常：{}",e.getMessage());
        return ResultTool.failMessage(ResultCode.COMMON_FAIL,e);
    }
}
