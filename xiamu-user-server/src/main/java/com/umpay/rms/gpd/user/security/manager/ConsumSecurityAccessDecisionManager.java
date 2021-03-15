package com.umpay.rms.gpd.user.security.manager;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * @program: rms-gpd
 * @description: 自定义认证管理器
 * @author: xiamu
 * @create: 2020-09-17 13:46
 */
@Component
public class ConsumSecurityAccessDecisionManager implements AccessDecisionManager {
    private List<AccessDecisionVoter<? extends Object>> decisionVoters;

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        System.out.println("collection>>" + configAttributes);

        for (ConfigAttribute configAttribute : configAttributes) {
            // 当前请求需要的权限
            String needRole = configAttribute.getAttribute();
            // 当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            System.out.println("authorities=" + authorities);
            if(CollectionUtils.isEmpty(authorities)){
                return;
            }
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("无访问权限");
    }

    /**
     * 复制默认方法，使得@PreAuthorize("hasRole('ROLE_ADMIN')") 可用
     */
    @Override
    public boolean supports(ConfigAttribute attribute) {
        for (AccessDecisionVoter voter : this.decisionVoters) {
            if (voter.supports(attribute)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        for (AccessDecisionVoter voter : this.decisionVoters) {
            if (!voter.supports(clazz)) {
                return false;
            }
        }
        return true;
    }



}
