package com.umpay.rms.gpd.user.util;

/**
 * @author Zzzxb
 * @date 2020/9/9 21:57
 * @description
 */
public class StringUtils {

    /**
     * xbto 目前没有好办法，先将就着吧
     * 把空串转为null
     * @param s
     * @return
     */
    public static String emptyToNull(String s) {
        return s != null && "".equals(s.trim())? null: s;
    }
}
