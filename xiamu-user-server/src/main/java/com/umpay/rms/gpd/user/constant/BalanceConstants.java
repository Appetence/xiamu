package com.umpay.rms.gpd.user.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @date 2020/8/19
 */
@Component
public class BalanceConstants {

    public static Long REGIST_GIVE_AUDIO_BALANCE = 10l;
    public static Long REGIST_GIVE_SMS_BALANCE = 10l;
    public static Long REGIST_GIVE_VEDIO_BALANCE = 10l;

    public static Long getRegistGiveAudioBalance() {
        return REGIST_GIVE_AUDIO_BALANCE;
    }

    @Value("${all.in.one.regist.give.audio.balance}")
    public void setRegistGiveAudioBalance(Long registGiveAudioBalance) {
        REGIST_GIVE_AUDIO_BALANCE = registGiveAudioBalance;
    }

    public static Long getRegistGiveVedioBalance() {
        return REGIST_GIVE_VEDIO_BALANCE;
    }

    @Value("${all.in.one.regist.give.vedio.balance}")
    public void setRegistGiveVedioBalance(Long registGiveVedioBalance) {
        REGIST_GIVE_VEDIO_BALANCE = registGiveVedioBalance;
    }

    public static Long getRegistGiveSmsBalance() {
        return REGIST_GIVE_SMS_BALANCE;
    }

    @Value("${all.in.one.regist.give.sms.balance}")
    public void setRegistGiveSmsBalance(Long registGiveSmsBalance) {
        REGIST_GIVE_SMS_BALANCE = registGiveSmsBalance;
    }
}
