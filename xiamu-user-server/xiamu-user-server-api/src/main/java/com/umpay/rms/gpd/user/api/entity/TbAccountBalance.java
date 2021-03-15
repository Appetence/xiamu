package com.umpay.rms.gpd.user.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (TbAccountBalance)实体类
 *
 * @author makejava
 * @since 2020-06-14 22:37:11
 */
@Data
public class TbAccountBalance implements Serializable {
    private static final long serialVersionUID = -23521150623883100L;
    
    private Integer id;
    /**
    * 账户id
    */
    private String acctId;
    /**
    * 账户余额
    */
    private Long balance;
    /**
    * 状态1，有效，2，无效
    */
    private Integer status;
    
    private Date createTime;
    /**
    * 总购买条数
    */
    private Long sumBalance;
    //本周充值条数
    private Integer weekRechange;
    //类型 1 普通短信
    private Integer busi;

    public Integer getWeekRechange() {
        return weekRechange;
    }

    public void setWeekRechange(Integer weekRechange) {
        this.weekRechange = weekRechange;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getSumBalance() {
        return sumBalance;
    }

    public void setSumBalance(Long sumBalance) {
        this.sumBalance = sumBalance;
    }
}