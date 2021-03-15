package com.umpay.rms.gpd.user.service.impl;


import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.user.util.CommonMessageUtil;
import com.umpay.rms.gpd.user.util.rsa.Base64Utils;
import com.umpay.rms.gpd.user.util.security.RSAUtils;
import com.umpay.rms.gpd.user.service.UserKeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.util.concurrent.TimeUnit;

/**
 * @program: rms-gpd
 * @description: 登录加密
 * @author: xiamu
 * @create: 2020-07-24 14:04
 */
@Service
public class UserKeyServiceImpl implements UserKeyService {

    private static Logger logger = LoggerFactory.getLogger(UserKeyServiceImpl.class);
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
//MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCx3IaITWgIiWWPJCU36HG+fITsvnAQiYpo0IZZMyiP09CP9tsPRt37Gj7rcSvQTdsVSfAi7ttIG87f6fNkrRDUhBIkZfHYaNfzoF87TsYOyByfW/zLbleQTkKttaDtw5e86vRJoL7WtAUah1RI6pTwM7D974f00B464gzf6TuPWQIDAQAB
//MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCprTxo8pBEOrpHWbPfd1Y83ze68dCh5d0QZNdIDptbg4KTuM8cD1NnQE2lTzkEUdrgd2PeshgU4ev2+ip2H/jzjidDaDF1kCqruYRXwrEnlcQChI4c0Fv1vp7EuT4UzefxXqabM3MkrpYlgIB0DkryJLLsTvpRJXI3wpDuZxCZCwIDAQAB
//MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKmtPGjykEQ6ukdZs993VjzfN7rx0KHl3RBk10gOm1uDgpO4zxwPU2dATaVPOQRR2uB3Y96yGBTh6/b6KnYf+POOJ0NoMXWQKqu5hFfCsSeVxAKEjhzQW/W+nsS5PhTN5/FeppszcySuliWAgHQOSvIksuxO+lElcjfCkO5nEJkLAgMBAAECgYBO69AEdIab5cENPPEISQaoq57NanqDQ1qOrIjh8EFc0I+kzDoxMAO4K9WxxWac1sH61C10wNuooujRaMzlQrvVMwfbmRFU7kB5WjZQxGN5jlvGVrs3a9oZtDkkvDQhL/xsxbGO2BlffOnKVPWq2n9YSxLK6+VGdtruxwvxHf+dIQJBAN5oGcCSHf90LFqDb57ZpIh9uhQa6c+Rtyp3sU2RNV+Dqx4ZoqROkRFj7stgbXoGSEYK3uDmTH797Wd3qY8to7sCQQDDTjPiYnsZ6wOrXLCn9Yb7y5bDqY8az7lH7ruzBLsMsPFjW62GWSTZSnCy24yJSCRi70B1qPkAPDpIMmczVgLxAkEAmy0K+r94Sqol4ClgkfbMw6blU+3LQNYsdGb6wGIWWUQm+mNshTOEKBwPmrr253LGrogbJdPJf7lwEQb+uTVgzQJAbk+FTqCBFyp+cBNyU+uBykY7aVgj0+jvpYlZysspOwnVzWDCqdc/0NsSvHL09bUhmB+6MLq3qUJbSNvvHje+gQJBAIbTONzpGPpPx1vsLhqR6UdFvNUMEbPH0LFt84FYQzo+pv+iIRqTvIc6oRhe+aORiAXvpX7wV9BLWbG2a+vrn6s=
//MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCprTxo8pBEOrpHWbPfd1Y83ze68dCh5d0QZNdIDptbg4KTuM8cD1NnQE2lTzkEUdrgd2PeshgU4ev2+ip2H/jzjidDaDF1kCqruYRXwrEnlcQChI4c0Fv1vp7EuT4UzefxXqabM3MkrpYlgIB0DkryJLLsTvpRJXI3wpDuZxCZCwIDAQAB
    @Override
    public Msg createKey(String jensionId) {
        try {
            KeyPair keyPair = RSAUtils.getKeyPair();
            String privateKey = new String(Base64Utils.encoder(keyPair.getPrivate().getEncoded()));
            String publicKey = new String(Base64Utils.encoder(keyPair.getPublic().getEncoded()));
            logger.info("public key :{}",publicKey);
            logger.info("private key :{}",privateKey);
            Long diff = 5 * 60L;//有效期
            try {
                //因为Template中set值时会先调用序列化器将键和值都序列化为byte字节数组放入redis数据库中，在客户端除非get后的key为“keyTest”使用同样的序列化器序列化后的值，否则取不到zh值
                redisTemplate.opsForValue().set(CommonMessageUtil.PRIVATE + jensionId, privateKey, diff, TimeUnit.SECONDS);
                redisTemplate.opsForValue().set(CommonMessageUtil.PUBLIC + jensionId, publicKey, diff, TimeUnit.SECONDS);
            } catch (Throwable throwable) {
                logger.error("登录操作redis异常：{}", throwable);
            }
           // Map<String,String> map = new HashMap<>();
           // map.put("private",privateKey);
            //map.put("public",publicKey);
            return Msg.returnSuccessMsg("",publicKey);
        } catch (Exception e) {
            logger.error("获取密钥错误：{}", e);
            return Msg.returnErrorMsg("获取密钥错误");
        }

    }

    @Override
    public Msg getKey(String jensionId) {
        try {
            if(redisTemplate.hasKey(jensionId)){
                Object string = redisTemplate.opsForValue().get(jensionId);//
                return Msg.returnSuccessMsg("",String.valueOf(string));
            }else {
                return Msg.returnErrorMsg("超时，请刷新页面后重试");
            }

        } catch (Throwable throwable) {
            return Msg.returnErrorMsg("redis 获取密钥异常:{}", throwable);
        }
    }
}
