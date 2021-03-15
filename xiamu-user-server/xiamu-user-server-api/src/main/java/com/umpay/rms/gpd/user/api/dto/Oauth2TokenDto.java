package com.umpay.rms.gpd.user.api.dto;



import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @program: xiaomu-oauth
 * @description: Oauth2获取Token返回信息封装
 * @author: xiamu
 * @create: 2021-03-04 20:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Oauth2TokenDto {
    /**
     * 访问令牌
     */
    private String token;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 访问令牌头前缀
     */
    private String tokenHead;
    /**
     * 有效时间（秒）
     */
    private int expiresIn;
}
