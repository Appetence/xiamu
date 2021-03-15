package com.umpay.rms.gpd.user.security.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.LogoutSuccessEvent;

/**
 * @program: xiaomu-oauth
 * @description:
 * @author: xiamu
 * @create: 2021-03-04 20:35
 */
@Slf4j
public class LogoutSuccessListener implements ApplicationListener<LogoutSuccessEvent> {
    @Override
    public void onApplicationEvent(LogoutSuccessEvent logoutSuccessEvent) {
        log.info("用户退出登陆");
    }
}
