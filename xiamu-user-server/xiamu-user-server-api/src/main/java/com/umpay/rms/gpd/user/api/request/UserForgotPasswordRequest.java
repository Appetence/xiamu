package com.umpay.rms.gpd.user.api.request;


import com.umpay.rms.gpd.internal.dto.basic.ReqBasic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @date 2020/8/21
 */
@Data
@ApiModel(value = "用户详情请求")
public class UserForgotPasswordRequest extends ReqBasic {

    /**
     * 手机号
     */
    @ApiModelProperty(value = "用户名", required = true,example = "15834260040")
    @Size(min = 2, max = 32)
    @NotNull
    private String mobile;
    /**
     * 密码
     */
    @ApiModelProperty(value = "新密码", required = true,example = "XNM1CR89")
    @Size(min = 8, max = 32)
    @NotNull
    private String password;
    /**
     * 图形验证码
     */
    @ApiModelProperty(value = "确认密码", required = true,example = "XNM1CR89")
    @Size(min = 8, max = 32)
    private String confrimPassword;


}
