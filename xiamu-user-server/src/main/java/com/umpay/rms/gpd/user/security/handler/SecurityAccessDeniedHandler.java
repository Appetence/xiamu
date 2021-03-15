package com.umpay.rms.gpd.user.security.handler;

import cn.hutool.json.JSONUtil;

import com.umpay.rms.gpd.internal.dto.Msg;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: rms-gpd
 * @description: 资源服务器权限不足
 * @author: xiamu
 * @create: 2020-06-03 14:58
 */
@Component
public class SecurityAccessDeniedHandler  implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        //返回json形式的错误信息
//        Msg result = Msg.returnErrorMsg("权限不足");
        Msg result = Msg.returnErrorMsg("您未提交企业资质认证或在认证中");
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSONUtil.toJsonStr(result));

    }
}
