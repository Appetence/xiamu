package com.umpay.rms.gpd.user.security.filter.client;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.BadClientCredentialsException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: rms-gpd
 * @description: 客户端不带完整client处理
 * @author: xiamu
 * @create: 2020-07-05 10:04
 */

public class ConsumClientCredentialsTokenEndpointFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();

    private boolean allowOnlyPost = false;

    public ConsumClientCredentialsTokenEndpointFilter() {
        this("/oauth/token");
    }

    public ConsumClientCredentialsTokenEndpointFilter(String path) {
        super(path);
        setRequiresAuthenticationRequestMatcher(new ConsumClientCredentialsTokenEndpointFilter.ClientCredentialsRequestMatcher(path));
        // If authentication fails the type is "Form"
        ((OAuth2AuthenticationEntryPoint) authenticationEntryPoint).setTypeName("Form");
    }

    public void setAllowOnlyPost(boolean allowOnlyPost) {
        this.allowOnlyPost = allowOnlyPost;
    }

    /**
     * @param authenticationEntryPoint the authentication entry point to set
     */
    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                AuthenticationException exception) throws IOException, ServletException {
                if (exception instanceof BadCredentialsException) {
                    exception = new BadCredentialsException(exception.getMessage(), new BadClientCredentialsException());
                }
                authenticationEntryPoint.commence(request, response, exception);
            }
        });
        setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                // no-op - just allow filter chain to continue to token endpoint
            }
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        if (allowOnlyPost && !"POST".equalsIgnoreCase(request.getMethod())) {
            throw new HttpRequestMethodNotSupportedException(request.getMethod(), new String[] { "POST" });
        }

        String clientId = request.getParameter("client_id");
        String clientSecret = request.getParameter("client_secret");
        /**
         * clientSecret 解密可以在这里做
         */
        // If the request is already authenticated we can assume that this
        // filter is not needed
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication;
        }

        if (clientId == null) {
            throw new BadCredentialsException("No client credentials presented");
        }

        if (clientSecret == null) {
            clientSecret = "";
        }

        clientId = clientId.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(clientId,
                clientSecret);

        return this.getAuthenticationManager().authenticate(authRequest);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }

    protected static class ClientCredentialsRequestMatcher implements RequestMatcher {

        private String path;

        public ClientCredentialsRequestMatcher(String path) {
            this.path = path;

        }

        @Override
        public boolean matches(HttpServletRequest request) {
            String uri = request.getRequestURI();
            int pathParamIndex = uri.indexOf(';');

            if (pathParamIndex > 0) {
                // strip everything after the first semi-colon
                uri = uri.substring(0, pathParamIndex);
            }

            String clientId = request.getParameter("client_id");

            if (clientId == null) {
                // Give basic auth a chance to work instead (it's preferred anyway)
                return false;
            }

            if ("".equals(request.getContextPath())) {
                return uri.endsWith(path);
            }

            return uri.endsWith(request.getContextPath() + path);
        }

    }

}
