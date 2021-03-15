package com.umpay.rms.gpd.user.security.listener;

import cn.hutool.json.JSONObject;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.*;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * @program: rms-gpd
 * @description: 监听AuthenticationFailureBadCredentialsEvent事件认证失败发出通知：
 * @author: xiamu
 * @create: 2020-07-11 11:31
 */
@Component
public class AuthenticationFailureListener
        implements ApplicationListener<AbstractAuthenticationFailureEvent> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
        if (event instanceof AuthenticationFailureBadCredentialsEvent) {
            //提供的凭据是错误的，用户名或者密码错误
            logger.info("---AuthenticationFailureBadCredentialsEvent---");
        } else if (event instanceof AuthenticationFailureCredentialsExpiredEvent) {
            //验证通过，但是密码过期
            logger.info("---AuthenticationFailureCredentialsExpiredEvent---");
        } else if (event instanceof AuthenticationFailureDisabledEvent) {
            //验证过了但是账户被禁用
            logger.info("---AuthenticationFailureDisabledEvent---");
        } else if (event instanceof AuthenticationFailureExpiredEvent) {
            //验证通过了，但是账号已经过期
            logger.info("---AuthenticationFailureExpiredEvent---");
        } else if (event instanceof AuthenticationFailureLockedEvent) {
            //账户被锁定
            logger.info("---AuthenticationFailureLockedEvent---");
        } else if (event instanceof AuthenticationFailureProviderNotFoundEvent) {
            //配置错误，没有合适的AuthenticationProvider来处理登录验证
            logger.info("---AuthenticationFailureProviderNotFoundEvent---");
        } else if (event instanceof AuthenticationFailureProxyUntrustedEvent) {
            //代理不受信任，用于Oauth、CAS这类三方验证的情形，多属于配置错误
            logger.info("---AuthenticationFailureProxyUntrustedEvent---");
        } else if (event instanceof AuthenticationFailureServiceExceptionEvent) {
            //其他任何在AuthenticationManager中内部发生的异常都会被封装成此类
            logger.info("---AuthenticationFailureServiceExceptionEvent---");
        }

    }
}
