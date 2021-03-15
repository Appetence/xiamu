package com.umpay.rms.gpd.user.security.jwt;

import com.umpay.rms.gpd.user.security.consum.OAuthUser;
import com.umpay.rms.gpd.user.util.CommonParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;

/**
 * @program: rms-gpd
 *  * 对JwtAccessTokenConverter 的 enhance进行重写，加入自定义的信息
 * @description: 自定义token
 * @author: xiamu
 * @create: 2020-06-07 14:48
 */
public class
ConsumJwtAccessTokenConverter extends JwtAccessTokenConverter {

    private static final String BEARER_PRIFIX = "bearer ";

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    //这个是token增强器，想让jwt token携带额外的信息在这里处理
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (accessToken instanceof DefaultOAuth2AccessToken) {
            Object principal = authentication.getPrincipal();

//这个principal是当时登录后存到securiy的东东，一般是用户实体，自己debug一下就知道了
            if (principal instanceof OAuthUser) {
                OAuthUser user = (OAuthUser) principal;
                HashMap<String, Object> map = new HashMap<>();
                //jwt默认已经自带用户名，无需再次加入
                map.put("status", user.getStatus());
                map.put("share_salt", user.getId());
                map.put("code", user.getCode());
                map.put("share_account", user.getAccountId());
                map.put("share_role", user.getRole());
                map.put("company", user.getCompany());
                map.put("login_key",this.incrementLoginKey(user.getUsername()));
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
            }
        }
        return super.enhance(accessToken, authentication);
    }
    public String incrementLoginKey(String username){
        //从redis中获取递增key
        String key = CommonParamsUtil.getLoginKey()+username;
        Long result = redisTemplate.opsForValue().increment(key,1);
        //无则加一返回
        return String.valueOf(result);
    }


    //主要是资源服务器解析时一定要有bearer这个头才认为是一个oauth请求，但不知道为啥指定jwt后这个头就不见了，特意加上去
    @Override
    protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
      //  return BEARER_PRIFIX + super.encode(accessToken, authentication);
        return   super.encode(accessToken, authentication);
    }

}
