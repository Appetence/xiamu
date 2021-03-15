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
@ApiModel(value = "手机号登录")
public class UserMobileLoginRequest extends ReqBasic {

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", required = true,example = "15834260040")
    @Size(min = 2, max = 11)
    @NotNull
    private String mobile;
    /**
     * 图形验证码
     */
    @ApiModelProperty(value = "图形验证码", required = true,example = "1234")
    @Size(min = 2, max = 6)
    @NotNull
    private String imageCode;
    /**
     * 短信验证码
     */
    @ApiModelProperty(value = "手机验证码", required = false,example = "123")
    @Size(min = 2, max = 8)
    @NotNull
    private String smsCode;
    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码", required = false,example = "123")
    @Size(min = 2, max = 8)
    private String password;
    /**
     * 业务类型 1 注册 2 忘记密码
     */
    @ApiModelProperty(value = "业务类型 1 注册 2 忘记密码", required = true,example = "1")
    @Size(min = 1, max = 2)
    @NotNull
    private Integer busi;

}
