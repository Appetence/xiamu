package com.umpay.rms.gpd.user.api.entity.vo;

/**
 * @author Zzzxb
 * @date 2020/9/14 03:33
 * @description
 */
public class ChannelUserInfoVO {
    private String vmsCode;
    private Integer receipt;
    private Integer num;

    public String getVmsCode() {
        return vmsCode;
    }

    public void setVmsCode(String vmsCode) {
        this.vmsCode = vmsCode;
    }

    public Integer getReceipt() {
        return receipt;
    }

    public void setReceipt(Integer receipt) {
        this.receipt = receipt;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
