package com.umpay.rms.gpd.user.security.consum;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: rms-gpd
 * @description: 定制 AccessToken 转换器，为添加额外信息在服务器端获取做准备
 * @author: xiamu
 * @create: 2020-07-20 18:39
 */

@Component
public class ConsumAccessTokenConverter extends DefaultAccessTokenConverter {

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
        OAuth2Authentication authentication
                = (OAuth2Authentication) super.extractAuthentication(claims);
        authentication.setDetails(claims);
        return authentication;
    }
}

