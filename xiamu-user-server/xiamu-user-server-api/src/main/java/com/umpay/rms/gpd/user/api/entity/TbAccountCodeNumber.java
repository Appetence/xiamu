package com.umpay.rms.gpd.user.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (TbAccountCodeNumber)实体类
 *
 * @author makejava
 * @since 2020-06-14 10:50:02
 */
public class TbAccountCodeNumber implements Serializable {
    private static final long serialVersionUID = 538494297360345238L;

    private Integer id;
    private String codeNumber;
    private Integer codeType;
    private String ciId;
    private String accountId;
    private String acctModelRespUrl;
    private String acctMmsRespUrl;
    private Integer rate;
    private Integer status;
    private String creater;
    private Date createTime;
    private String updater;
    private String updateTime;
    private String remark;
    private TbChannelInfo tbChannelInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(String codeNumber) {
        this.codeNumber = codeNumber;
    }

    public Integer getCodeType() {
        return codeType;
    }

    public void setCodeType(Integer codeType) {
        this.codeType = codeType;
    }

    public String getCiId() {
        return ciId;
    }

    public void setCiId(String ciId) {
        this.ciId = ciId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAcctModelRespUrl() {
        return acctModelRespUrl;
    }

    public void setAcctModelRespUrl(String acctModelRespUrl) {
        this.acctModelRespUrl = acctModelRespUrl;
    }

    public String getAcctMmsRespUrl() {
        return acctMmsRespUrl;
    }

    public void setAcctMmsRespUrl(String acctMmsRespUrl) {
        this.acctMmsRespUrl = acctMmsRespUrl;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public TbChannelInfo getTbChannelInfo() {
        return tbChannelInfo;
    }

    public void setTbChannelInfo(TbChannelInfo tbChannelInfo) {
        this.tbChannelInfo = tbChannelInfo;
    }
}