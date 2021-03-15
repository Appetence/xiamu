package com.umpay.rms.gpd.user.security.handler;

import cn.hutool.json.JSONUtil;

import com.umpay.rms.gpd.internal.dto.Msg;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Hutengfei
 * @Description: 匿名未登录的时候访问,需要登录的资源的调用类
 * @Date Create in 2019/9/3 21:35
 */
@Component
public class ConsumAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Throwable cause = e.getCause();
        Msg result =null;
        if(cause instanceof InvalidTokenException) {
            result = new Msg(501,"token 无效");
        }else{
            result = new Msg(501,"用户登录过期，请登录后重试");
        }
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSONUtil.toJsonStr(result));
    }
}
