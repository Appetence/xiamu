package com.umpay.rms.gpd.user.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: rms-gpd
 * @description: 用户详情请求
 * @author: xiamu
 * @create: 2020-11-02 10:05
 */
@Data
@ApiModel(value = "企业详情查询请求")
public class AccountInfoRequest {

    /**
     * 用户名（username）数组
     */
    @ApiModelProperty(value = "企业id数组", required = false, example = "15834260040")
    private String[] accountId;
}
