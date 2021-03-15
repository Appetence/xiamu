package com.umpay.rms.gpd.user.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: rms-gpd
 * @description: 通用公共参数
 * @author: xiamu
 * @create: 2020-07-28 10:55
 */
@Component
public class CommonParamsUtil {
    public static Long video;
    public static Long audio;
    public static Long texts;
    public static String jessionid;
    public static Long diff;
    public static Long accountFileInfo;
    private static String lockKey = "redisAccount";
    private static String prefix = "fastdfs";
    private static String loginKey = "loginKey:";

    public static String getLoginKey() {
        return loginKey;
    }
    @Value("${all.in.one.loginKey}")
    public void setLoginKey(String loginKey) {
        CommonParamsUtil.loginKey = loginKey;
    }

    public static String getLockKey() {
        return lockKey;
    }

    @Value("${all.in.one.lockKey}")
    public void setLockKey(String lockKey) {
        CommonParamsUtil.lockKey = lockKey;
    }

    public static Long getDiff() {
        return diff;
    }

    @Value("${all.in.one.diff}")
    public void setDiff(Long diff) {
        CommonParamsUtil.diff = diff;
    }

    public static String getJessionid() {
        return jessionid;
    }

    @Value("${all.in.one.jessionid}")
    public void setJessionid(String jessionid) {
        CommonParamsUtil.jessionid = jessionid;
    }

    public static Long getVideo() {
        return video;
    }

    @Value("${all.in.one.video}")
    public void setVideo(Long video) {
        CommonParamsUtil.video = video;
    }

    public static Long getAudio() {
        return audio;
    }

    @Value("${all.in.one.audio}")
    public void setAudio(Long audio) {
        CommonParamsUtil.audio = audio;
    }

    public static Long getTexts() {
        return texts;
    }

    @Value("${all.in.one.texts}")
    public void setTexts(Long texts) {
        CommonParamsUtil.texts = texts;
    }

    public static Long getAccountFileInfo() {
        return accountFileInfo;
    }
    @Value("${all.in.one.account.info.size}")
    public void setAccountFileInfo(Long accountFileInfo) {
        CommonParamsUtil.accountFileInfo = accountFileInfo;
    }

    public static String getPrefix() {
        return prefix;
    }
    @Value("${all.in.one.account.info.fastdfs.prefix}")
    public  void setPrefix(String prefix) {
        CommonParamsUtil.prefix = prefix;
    }
}
