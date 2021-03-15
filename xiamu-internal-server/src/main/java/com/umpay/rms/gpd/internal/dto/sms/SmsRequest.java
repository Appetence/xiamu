package com.umpay.rms.gpd.internal.dto.sms;

import lombok.Data;

/**
 * 功能描述
 *
 * @date 2020/10/23
 */
@Data
public class SmsRequest {
    /**
     * 模板ID
     */
    private String templateId;

    /**
     * 手机号
     */
    private String[] phones;

    /**
     * 替换内容
     */
    private String[] content;
}
