package com.umpay.rms.gpd.user.util;

/**
 * @author Zzzxb
 * @date 2020/9/10 10:13
 * @description
 */
public class IntegerUtils {
    public static Integer lessZeroToNull(Integer i) {
        return i != null && i < 0 ? null : i;
    }
}
