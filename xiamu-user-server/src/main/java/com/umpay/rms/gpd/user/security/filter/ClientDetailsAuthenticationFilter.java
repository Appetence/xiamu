package com.umpay.rms.gpd.user.security.filter;

import cn.hutool.json.JSONUtil;

import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.user.security.authentication.ConsumOAuth2Authentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * @program: rms-gpd
 * @description: 客户端不带完整client处理
 * @author: xiamu
 * @create: 2020-07-05 10:04
 */
@Component
public class ClientDetailsAuthenticationFilter extends OncePerRequestFilter {

    private ClientDetailsService clientDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 只有获取token的时候需要携带携带客户端信息，放过其他
        if (!"/oauth/token".equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        String[] clientDetails = this.isHasClientDetails(request);

        logger.debug("password ==>>>>{}"+request.getParameter("password"));
        if (clientDetails == null) {
            Msg resultVo = new Msg(HttpStatus.UNAUTHORIZED.value(), "请求中未包含客户端信息");
            // HttpUtilsResultVO.writerError(resultVo, response);
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(JSONUtil.toJsonStr(resultVo));
            return;
        }
        this.handle(request, response, clientDetails, filterChain);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response, String[] clientDetails, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {//已经包含权限信息
            filterChain.doFilter(request, response);
            return;
        }
        ClientDetails details = this.getClientDetailsService().loadClientByClientId(clientDetails[0]);
        /**
         * 自定义token
         */
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(details.getClientId(), details.getClientSecret(), details.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request, response);
    }

    /**
     * 判断请求头中是否包含client信息，不包含返回null  Base64编码
     */
    private String[] isHasClientDetails(HttpServletRequest request) {

        String[] params = null;

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {

            String basic = header.substring(0, 5);

            if (basic.toLowerCase().contains("basic")) {

                String tmp = header.substring(6);
                String defaultClientDetails = new String(Base64.getDecoder().decode(tmp));

                String[] clientArrays = defaultClientDetails.split(":");

                if (clientArrays.length != 2) {
                    return null;
                } else {
                    params = clientArrays;
                }

            }
        }
        String id = request.getParameter("client_id");
        String secret = request.getParameter("client_secret");

        if (header == null && id != null) {
            params = new String[]{id, secret};
        }
        return params;
    }

    public ClientDetailsService getClientDetailsService() {
        return clientDetailsService;
    }

    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }
}
