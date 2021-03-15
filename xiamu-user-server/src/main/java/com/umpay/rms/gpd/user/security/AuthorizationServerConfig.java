package com.umpay.rms.gpd.user.security;


import com.umpay.rms.gpd.user.security.exception.ConsumOAuth2WebResponseExceptionTranslator;
import com.umpay.rms.gpd.user.security.detail.FnpClientDetailsService;
import com.umpay.rms.gpd.user.security.filter.ClientDetailsAuthenticationFilter;
import com.umpay.rms.gpd.user.security.filter.ClientDetailsCodeAuthenticationFilter;
import com.umpay.rms.gpd.user.security.filter.client.ConsumClientCredentialsTokenEndpointFilter;
import com.umpay.rms.gpd.user.security.granter.RSATokenGranter;
import com.umpay.rms.gpd.user.security.granter.SMSTokenGranter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 认证服务器配置
 * <p>
 * 刷新token  uri
 * localhost:9402/oauth/token?grant_type=refresh_token&refresh_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzMSJdLCJ1c2VyX25hbWUiOiJqb2pvIiwic2NvcGUiOlsiYWxsIl0sImF0aSI6ImJmNWM2Y2Q5LTU1Y2QtNDE2ZS1iMWUwLTJhMDJmMTQ0OTY4NiIsImV4cCI6MTU5MTM1Mjg5NywiYXV0aG9yaXRpZXMiOlsiU3lzdGVtQ29udGVudERlbGV0ZSIsInAyIiwicDMiLCJTeXN0ZW1Vc2VyVmlldyIsIlN5c3RlbUNvbnRlbnRWaWV3IiwiU3lzdGVtIiwiU3lzdGVtQ29udGVudCIsIlN5c3RlbVVzZXIiLCJTeXN0ZW1Vc2VySW5zZXJ0IiwiU3lzdGVtVXNlckRlbGV0ZSIsIlN5c3RlbVVzZXJVcGRhdGUiLCJTeXN0ZW1Db250ZW50SW5zZXJ0IiwiU3lzdGVtQ29udGVudFVwZGF0ZSJdLCJqdGkiOiIwNTA5NWQzOC1jMTk5LTRhN2ItOWYyNi01YTgxOWJkZmMwNTMiLCJjbGllbnRfaWQiOiJhZG1pbiJ9.HQe-3gTBpNhLBoLD5Y4rgZTN9_AaztkbL3ECr96tleg&client_id=admin&client_secret=123
 * Created by macro on 2019/9/30.
 */
@Order(2)
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private ConsumOAuth2WebResponseExceptionTranslator webResponseExceptionTranslator;
    //令牌访问端点
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Resource
    private UserService userService;
    @Resource
    private ClientDetailsAuthenticationFilter clientDetailsAuthenticationFilter;
    @Resource
    private ClientDetailsCodeAuthenticationFilter clientDetailsCodeAuthenticationFilter;
    @Resource
    private ConsumClientCredentialsTokenEndpointFilter consumClientCredentialsTokenEndpointFilter;
    @Resource
    private ClientDetailsService clientDetailsService;
    @Resource
    private TokenStore tokenStore;
    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;
    @Resource
    private PasswordEncoder passwordEncoder;

    //客户端信息存储到数据库
    @Bean
    @Primary
    public ClientDetailsService clientDetailsService(DataSource dataSource) {
        ClientDetailsService clientDetailsService = new FnpClientDetailsService(dataSource);
        ((FnpClientDetailsService) clientDetailsService).setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);//设置授权码模式的授权码如何存取
    }

    /**
     * 】
     * 令牌管理服务  第二步
     *
     * @return
     */
    @Bean
    @Primary
    public AuthorizationServerTokenServices tokenServices() {
        // token生成方式

        ProjectToeknServices defaultTokenServices = new ProjectToeknServices();
        defaultTokenServices.setAccessTokenValiditySeconds(60 * 60 * 2);//令牌有效时间2H
        defaultTokenServices.setClientDetailsService(clientDetailsService);//客户端服务信息
        defaultTokenServices.setSupportRefreshToken(true);//是否刷新令牌
        defaultTokenServices.setTokenStore(tokenStore);//令牌存储策略
        //Jwt令牌使用，过滤器链上使用jwt生成令牌
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        defaultTokenServices.setRefreshTokenValiditySeconds(259200);//令牌默认刷新时间3T
        return defaultTokenServices;
    }
    private List<TokenGranter> getTokenGranters(ClientDetailsService clientDetails,
                                                AuthorizationServerTokenServices tokenServices,
                                                AuthorizationCodeServices authorizationCodeServices, OAuth2RequestFactory requestFactory) {
        List<TokenGranter> tokenGranters = new ArrayList<TokenGranter>();
        tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetails,requestFactory));
        tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetails, requestFactory));
        tokenGranters.add(new ImplicitTokenGranter(tokenServices, clientDetails, requestFactory));
        tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices, clientDetails, requestFactory));
        tokenGranters.add(new RSATokenGranter( userService,tokenServices,clientDetails,requestFactory));
        tokenGranters.add(new SMSTokenGranter( userService,tokenServices,clientDetails,requestFactory));
        if (authenticationManager != null) {
            tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices,clientDetails, requestFactory));
        }
        return tokenGranters;
    }
    /**
     * 令牌访问端点
     * 使用密码模式需要配置
     * 配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
     */
    @Override//配置令牌访问端点和令牌服务
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // super.configure(endpoints);
        endpoints.tokenGranter(
                new CompositeTokenGranter
                        (this.getTokenGranters(
                                endpoints.getClientDetailsService(),
                                endpoints.getTokenServices(),
                                endpoints.getAuthorizationCodeServices(),
                                endpoints.getOAuth2RequestFactory())));
        endpoints.authenticationManager(authenticationManager)//认证管理器，密码模式需要
                .authorizationCodeServices(authorizationCodeServices)//授权码模式需要
                .tokenServices(tokenServices())//令牌管理服务
                .userDetailsService(userService)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);//允许post方式提交
        endpoints.exceptionTranslator(webResponseExceptionTranslator);//异常处理
        // .allowedTokenEndpointRequestMethods(HttpMethod.POST,HttpMethod.GET);//允许post方式提交
    }

    /**
     * 客户端详情配置  第一步，具体配置信息在这里设置
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 令牌端点的安全约束
     * 对应于配置AuthorizationServer安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter核心过滤器
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许表单认证
        //这里增加拦截器到安全认证链中，实现自定义认证，包括图片验证，短信验证，微信小程序，第三方系统，CAS单点登录
        // 加载client的 获取接口
        clientDetailsCodeAuthenticationFilter.setClientDetailsService(clientDetailsService);
        clientDetailsAuthenticationFilter.setClientDetailsService(clientDetailsService);
        // 客户端认证之前的过滤器
        security.addTokenEndpointAuthenticationFilter(consumClientCredentialsTokenEndpointFilter);
        security.addTokenEndpointAuthenticationFilter(clientDetailsCodeAuthenticationFilter);
        security.addTokenEndpointAuthenticationFilter(clientDetailsAuthenticationFilter);
        //security.addTokenEndpointAuthenticationFilter(clientDetailsCodeAuthenticationFilter);

        security
                ////提供公有密钥的端点  开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // //资源服务器访问令牌解析端点 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("permitAll()");
                ////允许表单认证来申请令牌主要是让/oauth/token支持client_id以及client_secret作登录认证
                //allowFormAuthenticationForClients是为了注册clientCredentialsTokenEndpointFilter
                //clientCredentialsTokenEndpointFilter,解析request中的client_id和client_secret
                //构造成UsernamePasswordAuthenticationToken,然后通过UserDetailsService查询作简单的认证而已
                //一般是针对password模式和client_credentials
                //当然也可以使用http basic认证
                //如果使用了http basic认证,就不用使用clientCredentialsTokenEndpointFilter
                //因为本质是一样的
                //.allowFormAuthenticationForClients();
    }
}