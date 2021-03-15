package com.umpay.rms.gpd.user.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (DbMmsTemplateRelation)实体类
 *
 * @author makejava
 * @since 2020-06-08 14:31:13
 */
public class DbMmsTemplateRelation implements Serializable {
    private static final long serialVersionUID = 190679056716435148L;
    
    private Long id;
    /**
    * 用户id
    */
    private String acctId;
    /**
    * 模版id
    */
    private String templateId;
    /**
    * 网管类别 移动，联通，电信
    */
    private String gwId;
    /**
    * 网管模版id
    */
    private String gwTemplateId;
    /**
    * 设备扩展码
    */
    private String srcId;
    /**
    * 模版标题
    */
    private String templateTitle;
    /**
    * 状态1:未提交审核,2:审核中,3:审核通过,4:审核不通过
    */
    private String status;
    /**
    * 失败原因
    */
    private String reason;
    
    private Date createTime;
    
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getGwId() {
        return gwId;
    }

    public void setGwId(String gwId) {
        this.gwId = gwId;
    }

    public String getGwTemplateId() {
        return gwTemplateId;
    }

    public void setGwTemplateId(String gwTemplateId) {
        this.gwTemplateId = gwTemplateId;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public String getTemplateTitle() {
        return templateTitle;
    }

    public void setTemplateTitle(String templateTitle) {
        this.templateTitle = templateTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}