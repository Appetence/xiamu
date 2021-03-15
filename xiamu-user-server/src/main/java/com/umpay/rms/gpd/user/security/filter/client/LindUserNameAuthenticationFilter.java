package com.umpay.rms.gpd.user.security.filter.client;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: xiaomu-oauth
 * @description:    授权过滤器，你可以自定义它，并把它添加到默认过滤器前或者后去执行，主要用来到授权的持久化
 *                  它可以从请求上下文中获取你的user,password等信息，然后去判断它是否符合规则，最后通过authenticate方法去授权。
 *                  默认的UsernamePasswordAuthenticationFilter过滤器，主要判断请求方式是否为post，并且对username和password进行了默认值的处理，总之，在这个过滤器里不会涉及到具体业务。
 * @author: xiamu
 * @create: 2021-03-05 13:57
 */
public class LindUserNameAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public LindUserNameAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login", "GET"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null) {
            throw new InternalAuthenticationServiceException("Failed to get the username");
        }

        if (password == null) {
            throw new InternalAuthenticationServiceException("Failed to get the password");
        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}

