package com.umpay.rms.gpd.user.security;

import com.umpay.rms.gpd.user.security.handler.ConsumAuthenticationEntryPoint;
import com.umpay.rms.gpd.user.security.handler.SecurityAccessDeniedHandler;
import com.umpay.rms.gpd.user.security.manager.ConsumSecurityAccessDecisionManager;
import com.umpay.rms.gpd.user.security.source.UrlFilterInvocationSecurityMetadataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.annotation.Resource;

/**
 * 资源服务器配置
 *
 * @author macro
 * @date 2019/9/30
 */
@Order(4)
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)//注解方式认证
public class  ResouceServerConfig extends ResourceServerConfigurerAdapter {

    @Resource
    public TokenStore tokenStore;

    /**
     * 资源标识
     */
    public static final String RESOURCE_ID = "res1";

    /**
     * 权限不足处理
     *
     * @return
     */
    @Bean
    public AccessDeniedHandler getAccessDeniedHandler() {
        return new SecurityAccessDeniedHandler();
    }

    /**
     * token无效
     *
     * @return
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new ConsumAuthenticationEntryPoint();
    }

    @Resource
    private ConsumSecurityAccessDecisionManager accessDecisionManager;
    @Resource
    private UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(authenticationEntryPoint());//token无效
        resources.resourceId(RESOURCE_ID).tokenStore(tokenStore).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        //拦截所有的请求执行处理
        http.authorizeRequests()
                .antMatchers("/**").permitAll()//放行请求
                .and()
                .exceptionHandling()
                //权限不足
                .accessDeniedHandler(getAccessDeniedHandler())
                //匿名用户访问无权限资源时的异常处理;//sessionid无效
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .csrf()
                //csrf不拦截
                .ignoringRequestMatchers(new CsrfSecurityRequestMatcher())
                .and()
                .cors()
                .and()
                .sessionManagement()
                //如果有就支持session,
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}