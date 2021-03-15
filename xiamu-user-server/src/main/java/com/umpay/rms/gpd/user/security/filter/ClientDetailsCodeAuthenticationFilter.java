package com.umpay.rms.gpd.user.security.filter;


import cn.hutool.json.JSONUtil;

import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.user.service.LoginService;
import com.umpay.rms.gpd.user.service.UserKeyService;
import com.umpay.rms.gpd.user.util.CommonMessageUtil;
import com.umpay.rms.gpd.user.util.CommonParamsUtil;
import com.umpay.rms.gpd.user.util.requset.RequsetUtil;
import com.umpay.rms.gpd.user.util.rsa.RSAUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * @program: rms-gpd
 * @description: 客户端不带完整client处理
 * @author: xiamu
 * @create: 2020-07-05 10:04
 */
@Component
public class ClientDetailsCodeAuthenticationFilter extends OncePerRequestFilter {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RedisTemplate redisTemplate;
    @Resource
    private LoginService loginService;
    @Resource
    private UserKeyService userKeyService;

    private ClientDetailsService clientDetailsService;



    /**
     * 过滤路径
     */
    static final String AUTH_PATH = "/oauth/token";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 只有获取token的时候需要携带携带客户端信息，放过其他
        //logger.info("url:{}", request.getRequestURI().equals("/oauth/token"));
        if (request.getRequestURI().equals(AUTH_PATH)) {// && request.getParameter("code_number") != null && request.getParameter("code_number").equals("sms_code")

            String[] clientDetails = this.isHasClientDetails(request);
            String grant_type = request.getParameter("grant_type");
            if (StringUtils.isNotBlank(grant_type) && "refresh_token".equals(grant_type)) {
                //刷新token
                filterChain.doFilter(request, response);
                return;
            }
            if (clientDetails == null) {
                errorMessage(response, HttpStatus.UNAUTHORIZED.value(), "请求中未包含客户端信息");
            }
            //校验验证码
               this.checkCode(request, response, clientDetails, filterChain);
        } else {
            filterChain.doFilter(request, response);
            return;
        }
    }

    private void checkCode(HttpServletRequest request, HttpServletResponse response, String[] clientDetails, FilterChain filterChain) throws IOException, ServletException {
        //检验验证码类型
        String username = clientDetails[3];

        if (request.getParameter("code_type") != null && "sms_code".equals(request.getParameter("code_type"))) {
            String code = request.getParameter("code_number");
//          图像验证码
//            Msg msg = loginService.checkSmsCode(username, code);
            //短信验证码
//            Msg msg = loginService.checkSmsCodeForgot(username, code);
            Msg msg = Msg.returnSuccessMsg("");
            if (msg.getCode() == 200) {
                filterChain.doFilter(request, response);
            } else {
                errorMessage(response, HttpStatus.INTERNAL_SERVER_ERROR.value(), msg.getMessage());
            }
        } else if (request.getParameter("code_type") != null &&"simple_code".equals( request.getParameter("code_type") )) {
            //  if (c.getName().equals("JSESSIONID")) {
            String JsonId = (String) RequsetUtil.getJessionId(request, CommonParamsUtil.getJessionid()).getData();
            logger.info("登录类型：{}", "simple_code");
            String code = request.getParameter("code_number");
            Msg msg = loginService.checkCode(JsonId, code);
            if (msg.getCode() == 200) {
                //String key = request.getHeader("User-Keys");
               //根据jessionid修改私钥
               msg =  this.checkPrivateKey(JsonId,username);
                if (msg.getCode() == 200) {
                    filterChain.doFilter(request, response);
                }else {
                    errorMessage(response, HttpStatus.INTERNAL_SERVER_ERROR.value(), msg.getMessage());
                }
              /*  msg = checkPassword(clientDetails[clientDetails.length - 3], JsonId);
                //判断密码是否过期
                if (msg.getCode() == 200) {
                    *//*2.解密出加密串，我和前台约定的是JSON,获取到JSON我将其转换为map，这里我直接用手动封装map代替*//*
                    logger.info("解密后密码：{}", msg.getMessage());
                    *//*如果请求路径是为app,进行过滤对参数parameter内容解密，放入request.parameter中*//*
                    // if (request.getRequestURI().indexOf(AUTH_PATH) != -1) {
                    *//*2.解密出加密串，我和前台约定的是JSON,获取到JSON我将其转换为map，这里我直接用手动封装map代替*//*
                    Map paramter = new HashMap(16);
                     paramter.put("password", msg.getMessage());
                    // paramter.put("password", "123");
                    ParameterRequestWrapper wrapper = new ParameterRequestWrapper(request, paramter);
                    filterChain.doFilter(wrapper, response);
                    //filterChain.doFilter(request,response);
                    //  }
                } else {
                    logger.info("密码解密失败《《《《《《《《《《《《《《《《《");
                    errorMessage(response, HttpStatus.INTERNAL_SERVER_ERROR.value(), msg.getMessage());
                }*/
            } else {
                errorMessage(response, HttpStatus.INTERNAL_SERVER_ERROR.value(), msg.getMessage());
            }
        } else{
            filterChain.doFilter(request, response);
        }/* {
            Msg resultVo = new Msg(HttpStatus.INTERNAL_SERVER_ERROR.value(), "请携带验证码信息进行访问！！！");
            // HttpUtilsResultVO.writerError(resultVo, response);
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(JSONUtil.toJsonStr(resultVo));
            return;
        }*/
    }

    private Msg checkPrivateKey(String JsionId, String username) {
        try {
            //从redis中拿出私钥
            Msg msg = userKeyService.getKey(CommonMessageUtil.PRIVATE + JsionId);
            if (msg.getCode() == 200) {
               //修改redis中key
                if(StringUtils.isNotBlank((String)msg.getData())){
                    redisTemplate.opsForValue().set(CommonMessageUtil.PRIVATE+username,msg.getData(),CommonParamsUtil.getDiff(), TimeUnit.SECONDS);
               return Msg.returnSuccessMsg("");
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            logger.error("密码处理异常:{}", e.getMessage());
            return Msg.returnErrorMsg("密码处理异常");
        }
        return Msg.returnErrorMsg("密码解析理异常");
    }

    private Msg checkPassword(String password, String JsionId) {
        try {
            //从redis中拿出私钥
            Msg msg = userKeyService.getKey(CommonMessageUtil.PRIVATE + JsionId);
            if (msg.getCode() == 200) {
                // RSA解密
                //MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKUKyPTc7VgKUQAQo6RQryCYUzb1/2BeWQvqsD8LKYVW3l8RURsuSaOyPaKZcSfmTo4qKIUdyjCPIUMlrXp1vp6NUdywn5O5hn7JmZ86QxgF2fOUzKAYjIxv2zl1HZkNX5mejCFaro1VeQ0YN86+/i4aNP+FK0cxuNQHQx8kzlEDAgMBAAECgYEApON1ik3/QmpsKqh1FYEWtnX5DXwQPHOGe4VpuFKqlPEJEen3IjTIladKNN0gVECgUyCDgxsUGheln7RQ8XwZiCvPSykccyBNG/qj0tkXDhyly2px6hQRTtDEBJ4qT+Mq1BUUpan0l+WU5x0UrLUSYDwsYRei+pUfO6S7farVEwECQQDR/lXuDJa8bPwoOYttmXKh3d3DPCML/yp/4Uj6AFQmcItP8Lw3D8JqMHVurqRSON+QwCArGUjmdE7oW/VT+M5hAkEAyTNQPWQpxtiPII4VGA1pkkMGYbTxEcM3q/8sUvTtZPHft8dQsDl9UuwVSP7AMiB6mTv0vxGX+y/vh+Ch4qbx4wJBAKaSy3MDRWa+RN9blkAgIpBHwCuoPly70eiSuiKltBqx17BX09URiTRWsDBWFD6soprd7CaD7hHwXM9wcpBjosECQQCgpslWC6lT7czy2KA9MVBvifcJgznNzgug2UIiBxGavYq9qndiDrVjxcAFJex+2fIqcnYtLjYlShQ/cXyxhk2zAkBaiwiHSJo56qMSQW2Ul1n0+80LS5O8vrd3pBsk/OkAg9bdqIqqhmdPpmB8gKYh3UoYccw9AJVnrIKvUnudOJmi
                //password = StringUtils.substringBeforeLast(password, ",");
                String decryptData = RSAUtils.decrypt(password, RSAUtils.getPrivateKey(msg.getMessage()));
                logger.info("解密后内容:" + decryptData);
                password = StringUtils.substringBeforeLast(decryptData, ",");
                String longtime = StringUtils.substringAfterLast(decryptData, ",");
                if (System.currentTimeMillis() - Long.valueOf(longtime) > 360 * 1000) {
                    return Msg.returnErrorMsg("用户登录超时!!!");
                } else {
                    logger.error("用户登录密码：{}", password);
                    return Msg.returnSuccessMsg(password);
                }
            } else {
                logger.info("获取私钥失败:{}", msg.getMessage());
                return Msg.returnErrorMsg("获取私钥失败!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("密码处理异常:{}", e.getMessage());
            return Msg.returnErrorMsg("密码处理异常!!");
        }

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
                    return params;
                } else {
                    params = clientArrays;
                }

            }
        }
        String id = request.getParameter("client_id");
        String secret = request.getParameter("client_secret");
        String scope = request.getParameter("scope");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String codenumber = request.getParameter("code_number");
        String codetype = request.getParameter("code_type");

        if (header == null && id != null) {
            params = new String[]{id, secret, scope, username, password, codenumber, codetype};
        }

        //根据mobileid去redis中取短信验证码
        return params;
    }

    public ClientDetailsService getClientDetailsService() {
        return clientDetailsService;
    }

    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    public void errorMessage(HttpServletResponse response, int code, String message) throws IOException {
        Msg resultVo = new Msg(code, message);
        // HttpUtilsResultVO.writerError(resultVo, response);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.toJsonStr(resultVo));
        return;
    }
}
