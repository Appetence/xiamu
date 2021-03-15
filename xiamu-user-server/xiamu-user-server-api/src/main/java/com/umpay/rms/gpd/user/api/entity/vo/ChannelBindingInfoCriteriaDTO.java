package com.umpay.rms.gpd.user.api.entity.vo;

/**
 * @author Zzzxb
 * @date 2020/9/10 18:58
 * @description
 */
public class ChannelBindingInfoCriteriaDTO {

    /**
     * id
     */
    private Integer id;

    /**
     * 账号id
     */
    private String accountId;

    /**
     * 旧通道id
     */
    private String oldChannelId;

    /**
     * 新通道id
     */
    private String newChannelId;

    /**
     * 码号
     */
    private String vmsCode;

    public Integer getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldChannelId() {
        return oldChannelId;
    }

    public void setOldChannelId(String oldChannelId) {
        this.oldChannelId = oldChannelId;
    }

    public String getNewChannelId() {
        return newChannelId;
    }

    public void setNewChannelId(String newChannelId) {
        this.newChannelId = newChannelId;
    }

    public String getVmsCode() {
        return vmsCode;
    }

    public void setVmsCode(String vmsCode) {
        this.vmsCode = vmsCode;
    }
}
