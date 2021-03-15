package com.umpay.rms.gpd.user.security.listener;


import com.umpay.rms.gpd.internal.util.SpringUtil;


import com.umpay.rms.gpd.user.api.entity.DbUser;
import com.umpay.rms.gpd.user.api.entity.DbUserLog;
import com.umpay.rms.gpd.user.service.DbUserLogService;
import com.umpay.rms.gpd.user.service.DbUserService;
import com.umpay.rms.gpd.user.util.ip.IpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * @program: rms-gpd
 * @description: 登录成功触发事件
 * @author: xiamu
 * @create: 2020-07-07 17:22
 */

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        //这里的事件源除了登录事件（UsernamePasswordAuthenticationToken）还有可能是token验证事件源（OAuth2Authentication）
        if (event.getSource().getClass().getName().equals("org.springframework.security.authentication.UsernamePasswordAuthenticationToken")) {
            //登录日志逻辑。。。
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) event.getSource();
            if (StringUtils.isNotBlank(usernamePasswordAuthenticationToken.getName()) && usernamePasswordAuthenticationToken.getDetails() != null) {
                logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>providerManager 登录成功时候触发<<<<<<<<<<<<<<<<<<<<<<<");
                RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
                DbUserLog dbUserLog = new DbUserLog();
                //登录
                String ipAddress = IpUtil.getIpAddress(request);
                try {
                    DbUserLogService dbUserLogService = SpringUtil.getBean(DbUserLogService.class);
                    DbUserService dbUserService = SpringUtil.getBean(DbUserService.class);
                    DbUser dbUser = new DbUser();
                    dbUser.setUsername(usernamePasswordAuthenticationToken.getName());
                    List<DbUser> userList = dbUserService.queryAll(dbUser);
                    if (CollectionUtils.isEmpty(userList)) {
                        logger.info("用户账户信息未查到到");
                    } else {
                        dbUserLog.setUid(userList.get(0).getId());
                    }
                    dbUserLog.setStatus(1);
                    dbUserLog.setUsername(dbUser.getUsername());
                    dbUserLog.setIp(ipAddress);
                    dbUserLog = dbUserLogService.insert(dbUserLog);
                    if (dbUserLog.getId() > 0) {
                        logger.debug("登录日志入库成功");
                    } else {
                        logger.info("登录日志入库失败 ,时间：{}----,用户：{}", new Date(), dbUser.getUsername());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }


    }
}




