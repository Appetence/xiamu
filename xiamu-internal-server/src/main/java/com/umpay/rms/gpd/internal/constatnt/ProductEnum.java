package com.umpay.rms.gpd.internal.constatnt;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 功能描述
 *
 * @author yueyi2019
 * @date 2020/9/1
 */
@Getter
@AllArgsConstructor
public enum ProductEnum implements CodeEnum  {
    /**
     * 视频短信
     */
    AUDIO_START(1,"视频短信"),
    /**
     * 普通短信
     */
    SMS_START(2, "普通短信");


    private int code;
    private String value;
}
