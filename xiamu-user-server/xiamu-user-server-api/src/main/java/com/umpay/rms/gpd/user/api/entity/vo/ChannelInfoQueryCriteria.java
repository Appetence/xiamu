package com.umpay.rms.gpd.user.api.entity.vo;

import java.util.Date;

/**
 * @author Zzzxb
 * @date 2020/9/7 17:11
 * @description
 */
public class ChannelInfoQueryCriteria{
    /**
     * 通道名称
     */
    private String channelName;

    /**
     * 通道编号
     */
    private String channelCode;

    /**
     * 通道用户名
     */
    private String channelUname;

    /**
     * 通道状态
     */
    private Integer channelStatus;

    /**
     * 是否可扩展
     */
    private Integer extensible;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    @Override
    public String toString() {
        return "ChannelInfoQueryCriteria{" +
                "channelName='" + channelName + '\'' +
                ", channelCode='" + channelCode + '\'' +
                ", channelUname='" + channelUname + '\'' +
                ", channelStatus=" + channelStatus +
                ", extensible=" + extensible +
                '}';
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelUname() {
        return channelUname;
    }

    public void setChannelUname(String channelUname) {
        this.channelUname = channelUname;
    }

    public Integer getChannelStatus() {
        return channelStatus;
    }

    public void setChannelStatus(Integer channelStatus) {
        this.channelStatus = channelStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getExtensible() {
        return extensible;
    }

    public void setExtensible(Integer extensible) {
        this.extensible = extensible;
    }
}
