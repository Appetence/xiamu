package com.umpay.rms.gpd.user.security;

import com.umpay.rms.gpd.user.security.consum.ConsumAccessTokenConverter;
import com.umpay.rms.gpd.user.security.jwt.ConsumJwtAccessTokenConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * \
 * 令牌认证方式
 */
@Configuration
public class TokenConfig {
    private Logger logger = LoggerFactory.getLogger(TokenConfig.class);
    @Autowired
    private ConsumAccessTokenConverter customAccessTokenConverter;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
       /* ConsumJwtAccessTokenConverter converter = new ConsumJwtAccessTokenConverter();
        //authorization
        String key = "allinone.jks";

        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(key), "allinone".toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("allinone");
        //如需自定义rsa可以使用这个
        //        converter.setKeyPair(RSAUtil.GetKeyPair());
        converter.setKeyPair(keyPair);
        //resource
        Resource resource = new ClassPathResource("public.txt");
        String publickey = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            publickey = br.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            // e.printStackTrace();
            logger.error("token authorization Exception:{}", e.getMessage());
        }
//        converter.setVerifierKey(publickey); //非对称秘钥，资源服务器使用该秘钥来验证
        //resource end

        //converter.setSigningKey(SIGNING_KEY); //对称秘钥，资源服务器使用该秘钥来验证*/
        ConsumJwtAccessTokenConverter converter = new ConsumJwtAccessTokenConverter();
        //authorization
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("fnp-jwt.jks"), "allinone".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("fnp-jwt"));
        //resource
        Resource resource = new ClassPathResource("public.txt");
        String publickey = null;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))){
            publickey = br.lines().collect(Collectors.joining("\n"));
        }catch (Exception e){
            // logger.error(e.getMessage(),e);
            logger.error("token authorization Exception:{}",e);
        }
        converter.setVerifierKey(publickey); //非对称秘钥，资源服务器使用该秘钥来验证
        //resource end

        //converter.setSigningKey(SIGNING_KEY); //对称秘钥，资源服务器使用该秘钥来验证
        converter.setAccessTokenConverter(customAccessTokenConverter);
        return converter;
    }
}
