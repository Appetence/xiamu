package com.umpay.rms.gpd.user.api.entity;


import org.omg.PortableInterceptor.INACTIVE;

import java.io.Serializable;

/**
 * (TblMtStat)实体类
 *
 * @author xiaoG
 * @since 2020-06-24 10:33:49
 */
public class TblMtStat implements Serializable {

    private Integer id;
    /**
     * 话单时间
     */
    private String billTime;
    /**
     * 账户标识
     */
    private String acctId;
    /**
     * 渠道类型
     */
    private String srcType;
    /**
     * 企业提交总数
     */
    private Integer submitTotal;
    /**
     * 提交成功总数
     */
    private Integer submitSuccess;    /**
     * 提交失败数
     */
    private Integer submitFail;
    /**
     * 回执成功
     */
    private Integer reportSuccess;
    /**
     * 回执失败
     */
    private Integer reportFailure;
    /**
     * 没有回执
     */
    private Integer reportUnknow;
    //成功率
    private Double sendSuccessRate;


    private String openTime;
    private String endTime;

    public Integer getSubmitFail() {
        return submitFail;
    }

    public void setSubmitFail(Integer submitFail) {
        this.submitFail = submitFail;
    }

    public Integer getReportUnknow() {
        return reportUnknow;
    }

    public void setReportUnknow(Integer reportUnknow) {
        this.reportUnknow = reportUnknow;
    }

    public Double getSendSuccessRate() {
        return sendSuccessRate;
    }

    public void setSendSuccessRate(Double sendSuccessRate) {
        this.sendSuccessRate = sendSuccessRate;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getSrcType() {
        return srcType;
    }

    public void setSrcType(String srcType) {
        this.srcType = srcType;
    }

    public Integer getSubmitTotal() {
        return submitTotal;
    }

    public void setSubmitTotal(Integer submitTotal) {
        this.submitTotal = submitTotal;
    }

    public Integer getSubmitSuccess() {
        return submitSuccess;
    }

    public void setSubmitSuccess(Integer submitSuccess) {
        this.submitSuccess = submitSuccess;
    }

    public Integer getReportSuccess() {
        return reportSuccess;
    }

    public void setReportSuccess(Integer reportSuccess) {
        this.reportSuccess = reportSuccess;
    }

    public Integer getReportFailure() {
        return reportFailure;
    }

    public void setReportFailure(Integer reportFailure) {
        this.reportFailure = reportFailure;
    }
}