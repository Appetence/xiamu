package com.umpay.rms.gpd.user.api.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 角色表(DbRole)实体类
 *
 * @author makejava
 * @since 2020-05-10 18:55:25
 */

public class DbRole implements Serializable {
    private static final long serialVersionUID = -50257157211623709L;
    private int id;
    /**
     * 父角色
     */
    private int parentId;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色英文名称
     */
    private String enname;
    /**
     * 备注
     */
    private String description;
    private Date created;
    private Date updated;
    /**
     * id
     */
    private String appid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}