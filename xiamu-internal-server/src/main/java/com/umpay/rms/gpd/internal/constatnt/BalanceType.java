package com.umpay.rms.gpd.internal.constatnt;

import lombok.Setter;

/**
 * @program: rms-gpd
 * @description:
 * @author: xiamu
 * @create: 2020-10-27 15:21
 */
@Setter
public class BalanceType {

    private static String SMS_BALANCE = "billing_sms:";

    private static String SUM_SMS_BALANCE = "sum_billing_sms:";

    private static String VEDIO_BALANCE = "billing:";

    private static String SUM_VEDIO_BALANCE = "sum_billing:";

    private static String SUM = "sum_";

    public static String getSmsBalance() {
        return SMS_BALANCE;
    }

    public static String getVedioBalance() {
        return VEDIO_BALANCE;
    }

    public static String getSUM() {
        return SUM;
    }

    public static String getSumSmsBalance() {
        return SUM_SMS_BALANCE;
    }

    public static String getSumVedioBalance() {
        return SUM_VEDIO_BALANCE;
    }
}
