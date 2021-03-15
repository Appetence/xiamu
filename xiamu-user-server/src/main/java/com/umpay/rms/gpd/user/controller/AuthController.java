package com.umpay.rms.gpd.user.controller;



import com.umpay.rms.gpd.internal.dto.JsonResult;
import com.umpay.rms.gpd.internal.dto.ResultTool;
import com.umpay.rms.gpd.internal.util.SpringUtil;
import com.umpay.rms.gpd.user.api.dto.Oauth2TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * @program: xiaomu-oauth
 * @description: 自定义Oauth2获取令牌接口
 * @author: xiamu
 * @create: 2021-03-04 20:38
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {


    /**
     * Oauth2登录认证
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public JsonResult<Oauth2TokenDto> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        TokenEndpoint tokenEndpoint = SpringUtil.getBean(TokenEndpoint.class);
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead("Bearer ").build();
        return ResultTool.success(oauth2TokenDto);
    }
}
