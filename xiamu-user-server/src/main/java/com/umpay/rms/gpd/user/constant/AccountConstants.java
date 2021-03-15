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
public class AccountConstants {

    private static String ACCOUNT_ID = "456841645858488300";

    @Value("${all.in.one.account.id}")
    public void setAccountId(String accountId) {
        ACCOUNT_ID = accountId;
    }

    public static String getAccountId() {
        return ACCOUNT_ID;
    }
}
