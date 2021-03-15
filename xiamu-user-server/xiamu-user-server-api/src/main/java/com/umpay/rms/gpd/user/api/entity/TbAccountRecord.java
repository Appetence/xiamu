package com.umpay.rms.gpd.user.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (TbAccountRecord)实体类
 *
 * @author makejava
 * @since 2020-06-14 22:37:16
 */
public class TbAccountRecord implements Serializable {
    private static final long serialVersionUID = 291230442903773992L;
    
    private Integer id;
    /**
    * 条数
    */
    private Long number;
    /**
    * 状态 1，有效，2，无效
    */
    private Integer status;
    /**
    * 操作，1，充值2，消费3，,分发
    */
    private Integer flag;
    /**
    * 用户账户id
    */
    private String accId;
    /**
    * 操作用户id
    */
    private String operatAccId;

    //操作user
    private Integer operatUserId;
    /**
    * 备注
    */
    private String remark;
    
    private Date createTime;

    private Long beforNumber;

    private int pageSize;

    private int pageNum;

    private int sumNumber;

    private String endTime;

    private String openTime;

    private String parentAccountId;
    //客户名称
    private String company;
    /**
     * 代理商名称
     */
    private String delegateName;
    /**
     * 客户编号
     */
    private String serial;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getDelegateName() {
        return delegateName;
    }

    public void setDelegateName(String delegateName) {
        this.delegateName = delegateName;
    }

    public int getSumNumber() {
        return sumNumber;
    }

    public void setSumNumber(int sumNumber) {
        this.sumNumber = sumNumber;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getParentAccountId() {
        return parentAccountId;
    }

    public void setParentAccountId(String parentAccountId) {
        this.parentAccountId = parentAccountId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getOperatUserId() {
        return operatUserId;
    }

    public void setOperatUserId(Integer operatUserId) {
        this.operatUserId = operatUserId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getBeforNumber() {
        return beforNumber;
    }

    public void setBeforNumber(Long beforNumber) {
        this.beforNumber = beforNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }




    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getAccId() {
        return accId;
    }

    public void setAcctId(String accId) {
        this.accId = accId;
    }

    public String getOperatAccId() {
        return operatAccId;
    }

    public void setOperatAccId(String operatAccId) {
        this.operatAccId = operatAccId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}