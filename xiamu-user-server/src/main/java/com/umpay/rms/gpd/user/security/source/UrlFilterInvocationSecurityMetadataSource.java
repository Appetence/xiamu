package com.umpay.rms.gpd.user.security.source;


import com.umpay.rms.gpd.user.api.entity.DbButton;
import com.umpay.rms.gpd.user.service.DbButtonService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.*;

/**
 * @program: rms-gpd
 * @description: 根据url获取url需要的访问权限
 * @author: xiamu
 * @create: 2020-09-17 14:00
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Logger logger = LoggerFactory.getLogger(UrlFilterInvocationSecurityMetadataSource.class);
    private AntPathMatcher antPathMatcher = new AntPathMatcher(); // 模糊匹配 如何 auth/**   auth/auth
    @Autowired
    private DbButtonService dbButtonService;
    @Autowired
    private TokenStore tokenStore;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Set<ConfigAttribute> set = new HashSet<>();
        //后期单点登录，用户登出，等都可以在这里校验一下token
        String requestUrl = ((FilterInvocation) object).getRequest().getRequestURI();
        logger.info("requestUrl >> {}", requestUrl);
        // 这里获取对比数据可以从数据库或者内存 redis等等地方获取 目前先写死后面优化
        String url = "/oauth/**";
        String urlLogin = "/login/**";
        if (antPathMatcher.match(url, requestUrl) || antPathMatcher.match(urlLogin, requestUrl)) {
            //权限部分都放开
/*            SecurityConfig securityConfig = new SecurityConfig("ROLE_ADMIN");
            set.add(securityConfig);*/
            return set;
        } else {
            String authorization = ((FilterInvocation) object).getRequest().getHeader("Authorization");
            if (StringUtils.isBlank(authorization)) {
                //TODO 先注释   没有携带token访问
                throw new InvalidTokenException("用户未登录");
            }
            url = "/**";
            //暂时所有请求都处理，后期有特殊需要单独维护
            if (antPathMatcher.match(url, requestUrl)) {
                //所有的uri 对应的权限需要
                List<DbButton> dbButtonList = dbButtonService.listByEntity(new DbButton());
                for (DbButton dbButton : dbButtonList) {
                    //url为空，表示该按钮只做展示不关联连接，无需做权限处理
                    if (StringUtils.isNotBlank(dbButton.getUrl()) && dbButton.getUrl().equals(requestUrl)) {
                        //ename为空表示该url无需任何权限即可访问
                        if (StringUtils.isNotBlank(dbButton.getEname())) {
                            return SecurityConfig.createList(dbButton.getEname());
                        }

                    }
                }
                //表示该请求没有维护过，无需考虑，直接提示吴权限  暂时因为url不固定，先都放开
                //return SecurityConfig.createList("unknow_access");
            }
        }
        return set;
    }

    public Map<String, Object> getExtraInfo(OAuth2Authentication auth) {
        Map<String, Object> map = (Map<String, Object>) auth.getDetails();
        return map;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}