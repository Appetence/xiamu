package com.umpay.rms.gpd.user.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @date 2020/8/19
 */
@Component
public class CodeConstants {
    /**
     * 注册次数记录有效时间
     */
    public static String CODE_REGISTER_EFFECTIVE_IIME = "7200";
    /**
     * 忘记密码记录有效时间
     */
    public static String CODE_FORGOT_EFFECTIVE_IIME = "7200";

    /**
     * 忘记密码短信验证码有效时间
     */
    public static String CODE_FORGOT_SMS_EFFECTIVE_IIME = "600";
    /**
     * 注册短信验证码有效时间
     */
    public static String CODE_REGISTER_SMS_EFFECTIVE_IIME = "600";

    public static String getCodeRegisterEffectiveIime() {
        return CODE_REGISTER_EFFECTIVE_IIME;
    }

    @Value("${all.in.one.code.register.effective.time}")
    public void setCodeRegisterEffectiveIime(String codeRegisterEffectiveIime) {
        CODE_REGISTER_EFFECTIVE_IIME = codeRegisterEffectiveIime;
    }

    public static String getCodeForgotEffectiveIime() {
        return CODE_FORGOT_EFFECTIVE_IIME;
    }

    @Value("${all.in.one.code.forgot.effective.time}")
    public void setCodeForgotEffectiveIime(String codeForgotEffectiveIime) {
        CODE_FORGOT_EFFECTIVE_IIME = codeForgotEffectiveIime;
    }

    public static String getCodeForgotSmsEffectiveIime() {
        return CODE_FORGOT_SMS_EFFECTIVE_IIME;
    }
    @Value("${all.in.one.code.forgot.sms.effective.time}")
    public  void setCodeForgotSmsEffectiveIime(String codeForgotSmsEffectiveIime) {
        CODE_FORGOT_SMS_EFFECTIVE_IIME = codeForgotSmsEffectiveIime;
    }

    public static String getCodeRegisterSmsEffectiveIime() {
        return CODE_REGISTER_SMS_EFFECTIVE_IIME;
    }
    @Value("${all.in.one.code.register.sms.effective.time}")
    public  void setCodeRegisterSmsEffectiveIime(String codeRegisterSmsEffectiveIime) {
        CODE_REGISTER_SMS_EFFECTIVE_IIME = codeRegisterSmsEffectiveIime;
    }



}
