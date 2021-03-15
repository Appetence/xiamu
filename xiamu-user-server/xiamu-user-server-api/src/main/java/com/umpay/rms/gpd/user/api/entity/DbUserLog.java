package com.umpay.rms.gpd.user.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (DbUserLog)实体类
 *
 * @author makejava
 * @since 2020-06-05 13:20:56
 */
public class DbUserLog implements Serializable {
    private static final long serialVersionUID = -70900644278594291L;
    
    private Integer id;
    /**
    * 手机号
    */
    private String username;
    /**
    * ip
    */
    private String ip;
    /**
    * 地址
    */
    private String address;
    
    private Date createTime;
    /**
    * 用户id
    */
    private Integer uid;
    /**
    * 状态，1，有效，2，无效
    */
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}