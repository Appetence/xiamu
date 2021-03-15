package com.umpay.rms.gpd.user.api.response;

import lombok.Data;

/**
 * @program: rms-gpd
 * @description: 账户余额响应
 * @author: xiamu
 * @create: 2020-10-27 15:30
 */
@Data
public class BalanceInfoResponse {
    //普通短信余额
    private String smsBalance;
    //视频短信余额
    private String vedioBalance;
    //普通短信总余额
    private String sumSmsBalance;
    //视频短信总余额
    private String sumVedioBalance;
}
