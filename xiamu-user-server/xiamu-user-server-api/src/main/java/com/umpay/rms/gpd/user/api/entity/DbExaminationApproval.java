package com.umpay.rms.gpd.user.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (DbExaminationApproval)实体类
 *
 * @author makejava
 * @since 2020-06-08 14:42:36
 */
public class DbExaminationApproval implements Serializable {
    private static final long serialVersionUID = 812735468126537216L;
    
    private Integer id;
    /**
    * 申请人
    */
    private String applicantId;
    /**
    * 审批人
    */
    private int approverId;
    
    private Date createTime;
    /**
    * 状态，1，有效，2，无效
    */
    private Integer status;
    /**
    * 审批结果，1，通过，2，驳回，3待审批
    */
    private Integer result;
    /**
    * 审批内容
    */
    private String content;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public int getApproverId() {
        return approverId;
    }

    public void setApproverId(int approverId) {
        this.approverId = approverId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}