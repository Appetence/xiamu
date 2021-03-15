package com.umpay.rms.gpd.internal.constatnt;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 身份类型
 *
 * @author
 */
@Getter
@AllArgsConstructor
public enum IdentityEnum implements CodeEnum {


    /**
     * 大屏
     */
    LARGE_SCREEN(4, "大屏"),

    /**
     *
     */
    OTHER(9, "其他身份");

    private final int code;
    private final String value;
}
