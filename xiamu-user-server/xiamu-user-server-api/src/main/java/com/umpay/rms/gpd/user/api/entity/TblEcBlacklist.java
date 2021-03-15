package com.umpay.rms.gpd.user.api.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TblEcBlacklist)实体类
 *
 * @author makejava
 * @since 2020-06-18 16:01:53
 */
public class TblEcBlacklist implements Serializable {
    private static final long serialVersionUID = -55730233911828290L;
    /**
     * 主键id
     */
    private String id;
    /**
     * 企业ID
     */
    private String acctId;
    /**
     * 业务类型
     */
    private String bizType;
    /**
     * 黑名单来源（1:页面，2:td）
     */
    private String originType;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 删除标志（0-可用，1-已删除）
     */
    private String delFlag;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private String remark;
    /**
     * 业务类型 1 全局 2 局部
     */
    private String range;
    private String openTime;
    private String endTime;
    //添加人
    private String username;
    //添加企业
    private String company;
    //用户id
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}