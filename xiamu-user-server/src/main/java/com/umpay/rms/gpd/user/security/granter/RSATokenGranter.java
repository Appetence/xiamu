package com.umpay.rms.gpd.user.security.granter;

import com.umpay.rms.gpd.user.controller.UserLogController;
import com.umpay.rms.gpd.user.security.UserService;
import com.umpay.rms.gpd.user.security.consum.OAuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Map;

/**
 * @program: rms-gpd
 * @description: 登录方式
 * @author: xiamu
 * @create: 2020-07-27 16:50
 */
public class RSATokenGranter extends AbstractTokenGranter {

    private Logger logger = LoggerFactory.getLogger(RSATokenGranter.class);
    private static final String GRANT_TYPE = "rsa_code";

    protected UserService userDetailsService;

    private OAuth2RequestFactory requestFactory;

    public RSATokenGranter(UserService userDetailsService,
                           AuthorizationServerTokenServices tokenServices,
                           ClientDetailsService clientDetailsService,
                           OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.userDetailsService=userDetailsService;
        this.requestFactory=requestFactory;
    }



    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        Map<String, String> parameters = tokenRequest.getRequestParameters();
        OAuthUser user = null;
        try {
            user = this.getUser(parameters);
            /**
             * 获取一个递增的key
             * 携带到token中
             * 每次请求校验key的一致性
             */
        } catch (Exception e) {
            logger.error("无法获取用户信息",e);
            throw new InvalidGrantException("无法获取用户信息");
        }
        if (user == null) {
            logger.warn("无法获取用户信息");
            throw new InvalidGrantException("无法获取用户信息");
        }
        OAuth2Request storedOAuth2Request = this.requestFactory.createOAuth2Request(client, tokenRequest);
        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(user, null, user.getAuthorities());
        authentication.setDetails(user);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(storedOAuth2Request, authentication);
        UserLogController.logInfo(parameters.get("username"));
        return oAuth2Authentication;
    }

    private OAuthUser getUser(Map<String,String> params) throws Exception {
        return userDetailsService.loadUserByUsernameAndSmscode(params.get("username"),params.get("password"));
    }

}
