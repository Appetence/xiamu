package com.umpay.rms.gpd.user.api.pojo;



import com.umpay.rms.gpd.internal.dto.basic.ReqBasic;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: rms-gpd
 * @description: 短信发送详情
 * @author: xiamu
 * @create: 2020-11-02 13:34
 */
@Data
@ApiModel(value = "短信发送详情")
public class SmsSendInfo extends ReqBasic implements Serializable {

    private String accountId;
    private String mobile;
    private String code;
    private String content="【联动优势】验证码：XXXXXX。此验证码只用于登录5G融合消息平台，请勿转发他人，10分钟内有效";

}
