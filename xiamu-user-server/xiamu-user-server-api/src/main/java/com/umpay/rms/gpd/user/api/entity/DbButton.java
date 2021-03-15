package com.umpay.rms.gpd.user.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (DbButton)实体类
 *
 * @author xiaoG
 * @since 2020-06-29 14:27:07
 */
public class DbButton implements Serializable {
    
        private Integer id;
    /**
    * 名称
    */
        private String name;
    /**
    * 英文名称
    */
        private String ename;
    /**
    * 路径
    */
        private String url;
    /**
    * 描述
    */
        private String description;
    /**
    * 创建时间
    */

    private Date createTime;
    /**
    * 应用id
    */
        private Integer appid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }
}