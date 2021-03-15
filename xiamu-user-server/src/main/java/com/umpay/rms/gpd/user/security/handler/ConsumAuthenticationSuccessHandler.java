package com.umpay.rms.gpd.user.security.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: rms-gpd
 * @description:登录成功处理类,登录成功后会调用里面的方法
 * @author: xiamu
 * @create: 2020-06-03 15:51
 */



@Component
public class ConsumAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    Logger logger = LoggerFactory.getLogger( this.getClass() );

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //简单的说就是获取当前用户，拿到用户名或者userId，创建token，返回
        logger.info("登陆成功...");
     /*   CustomerUserDetails principal = (CustomerUserDetails) authentication.getPrincipal();
        //颁发token
        Map<String,Object> emptyMap = new HashMap<>(4);
        emptyMap.put(UserConstants.USER_ID,principal.getId());
        String token = JwtTokenUtil.generateToken(principal.getUsername(), emptyMap);
        ResponseUtil.out(ResultUtil.success(token));*/
    }
}
