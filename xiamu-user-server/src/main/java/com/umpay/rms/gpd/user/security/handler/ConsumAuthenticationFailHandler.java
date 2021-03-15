package com.umpay.rms.gpd.user.security.handler;


import cn.hutool.json.JSONUtil;
import com.umpay.rms.gpd.internal.dto.JsonResult;
import com.umpay.rms.gpd.internal.dto.ResultCode;
import com.umpay.rms.gpd.internal.dto.ResultTool;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @program: rms-gpd
 * @description:登录账号密码错误等情况下,会调用的处理类
 * @author: xiamu
 * @create: 2020-06-03 16:18
 */
@Component
public class ConsumAuthenticationFailHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,  AuthenticationException e) throws IOException, ServletException {
        JsonResult result = ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.toJsonStr(result));
    }

}

