package com.umpay.rms.gpd.user.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.Collection;

/**
 * @program: rms-gpd
 * @description: 自定义认证
 * @author: xiamu
 * @create: 2020-07-20 18:44
 */
public class ConsumOAuth2Authentication extends OAuth2Authentication {

    private Object ConsumerUserInfo;

    public Object getConsumerUserInfo() {
        return ConsumerUserInfo;
    }

    public void setConsumerUserInfo(Object consumerUserInfo) {
        ConsumerUserInfo = consumerUserInfo;
    }

    /**
     * Construct an OAuth 2 authentication. Since some grant types don't require user authentication, the user
     * authentication may be null.
     *
     * @param storedRequest      The authorization request (must not be null).
     * @param userAuthentication The user authentication (possibly null).
     */
    public ConsumOAuth2Authentication(OAuth2Request storedRequest, Authentication userAuthentication) {
        super(storedRequest, userAuthentication);
    }
}
