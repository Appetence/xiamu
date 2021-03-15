package com.umpay.rms.gpd.user.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: rms-gpd
 * @description:
 * @author: xiamu
 * @create: 2020-10-31 15:17
 */
@Component
public class RoleConstants {

    private static int REGIST__RULE = 40;

    public static int getRegist_rule() {
        return REGIST__RULE;
    }
    @Value("${all.in.one.regist.rule}")
    public void setRegist_rule(int regist_rule) {
        REGIST__RULE = regist_rule;
    }
}
