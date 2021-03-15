package com.umpay.rms.gpd.internal.constatnt;

import lombok.Getter;

/**
 * @author yueyi2019
 */
@Getter
public enum BusinessInterfaceStatus implements CodeEnum {

    /**
     * 操作成功
     */
    SUCCESS(200, "success"),

    /**
     * 操作失败
     */
    FAIL(500, "fail");

    private final int code;
    private final String value;

    BusinessInterfaceStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
