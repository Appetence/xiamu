package com.umpay.rms.gpd.user.security.handler;


import cn.hutool.json.JSONUtil;
import com.umpay.rms.gpd.internal.dto.JsonResult;
import com.umpay.rms.gpd.internal.dto.ResultCode;
import com.umpay.rms.gpd.internal.dto.ResultTool;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: rms-gpd
 * @description: 暂时没有用到 自定义登录失败处理器
 * @author: xiamu
 * @create: 2020-06-05 18:15
 */

@Component
public class JWTAuthenticationFailHandler  extends SimpleUrlAuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        //返回json形式的错误信息
        JsonResult result = ResultTool.fail(ResultCode.USER_ACCOUNT_EXPIRED);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.toJsonStr(result));
    }
}