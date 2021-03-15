package com.umpay.rms.gpd.user.security;

import com.umpay.rms.gpd.user.security.filter.client.ConsumClientCredentialsTokenEndpointFilter;
import com.umpay.rms.gpd.user.security.filter.client.LindUserNameAuthenticationFilter;
import com.umpay.rms.gpd.user.security.dao.LindAuthenticationProvider;
import com.umpay.rms.gpd.user.security.handler.ConsumAuthenticationFailHandler;
import com.umpay.rms.gpd.user.security.handler.ConsumAuthenticationSuccessHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * SpringSecurity配置，
 * Created by macro on 2019/10/8.
 */
@Order(6)
@Configuration
@EnableWebSecurity
//@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private ConsumAuthenticationFailHandler consumAuthenticationFailHandler;
    @Autowired
    private ConsumAuthenticationSuccessHandler consumAuthenticationSuccessHandler;
    @Autowired
    LindAuthenticationProvider lindAuthenticationProvider;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean//加密模式，密码编码器
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 在这里配置是因为所有的请求走的都是交给security来完成的
     *
     * @param http
     * @throws Exception
     */
    @Override//安全拦截机制
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(lindAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(consumClientCredentialsTokenEndpointFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                //放行请求
                .antMatchers("/login/**", "/logout/**")
                .permitAll()
                .anyRequest()//需要认证请求
                .authenticated()
                .and()
                .cors()
                .and()
                .sessionManagement()
                //session管理，一个四种，never从不创建,ifrequired如果登录时候需要就创建 alway如果没有及创建  stateles不会创建也不会使用session restful时使用多
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().csrf().ignoringRequestMatchers(new CsrfSecurityRequestMatcher());
                //配置取消session管理,又Jwt来获取用户状态,否则即使token无效,也会有session信息,依旧判断用户为登录状态

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui.html")
                .antMatchers("/webjars/**")
                .antMatchers("/v2/**")
                .antMatchers("/swagger-resources/**");
    }

    /**
     * 自定义的Filter.
     */
    @Bean
     LindUserNameAuthenticationFilter lindAuthenticationFilter() {
        LindUserNameAuthenticationFilter phoneAuthenticationFilter = new LindUserNameAuthenticationFilter();
        ProviderManager providerManager =
                new ProviderManager(Collections.singletonList(lindAuthenticationProvider));
        phoneAuthenticationFilter.setAuthenticationManager(providerManager);
        phoneAuthenticationFilter.setAuthenticationSuccessHandler(consumAuthenticationSuccessHandler);
        phoneAuthenticationFilter.setAuthenticationFailureHandler(consumAuthenticationFailHandler);
        return phoneAuthenticationFilter;
    }
    /**
     * 自定义的Filter.
     */
    @Bean
    ConsumClientCredentialsTokenEndpointFilter consumClientCredentialsTokenEndpointFilter() {
        ConsumClientCredentialsTokenEndpointFilter phoneAuthenticationFilter = new ConsumClientCredentialsTokenEndpointFilter();
        ProviderManager providerManager =
                new ProviderManager(Collections.singletonList(lindAuthenticationProvider));
        phoneAuthenticationFilter.setAuthenticationManager(providerManager);
        phoneAuthenticationFilter.setAuthenticationSuccessHandler(consumAuthenticationSuccessHandler);
        phoneAuthenticationFilter.setAuthenticationFailureHandler(consumAuthenticationFailHandler);
        return phoneAuthenticationFilter;
    }
/**
 *  security 自定义uri认证
 AffirmativeBased 一票通过，只要有一个投票器通过就允许访问

 ConsensusBased 有一半以上投票器通过才允许访问资源

 UnanimousBased 所有投票器都通过才允许访问

 @Bean public AccessDecisionManager accessDecisionManager() {
 List<AccessDecisionVoter<? extends Object>> decisionVoters= Arrays.asList(new WebExpressionVoter(),
 // new RoleVoter(),
 new RoleBasedVoter(),
 new AuthenticatedVoter());
 return new UnanimousBased(decisionVoters);
 }*/

}