package com.umpay.rms.gpd.user.api.request;


import com.umpay.rms.gpd.internal.dto.basic.ReqBasic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;


/**
 * @date 2020/8/21
 */
@Data
@ApiModel(value = "用户注册")
public class UserRegisterRequest extends ReqBasic {

    /**
     * 手机号
     */
    @ApiModelProperty(value = "用户名", required = true,example = "xxx")
    @Size(min = 2, max = 32)
    private String mobile;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true,example = "xxx")
    @Size(min = 8, max = 32)
    private String password;
    /**
     * 短信验证码
     */
    @ApiModelProperty(value = "短信验证码", required = true,example = "xxx")
    @Size(min = 6, max = 32)
    private String smsCode;


}
