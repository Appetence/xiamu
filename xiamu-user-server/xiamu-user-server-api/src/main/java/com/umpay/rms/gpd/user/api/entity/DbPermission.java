package com.umpay.rms.gpd.user.api.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 权限表(DbPermission)实体类
 *
 * @author makejava
 * @since 2020-05-10 18:55:17
 */


public class DbPermission implements Serializable {
    private static final long serialVersionUID = -21899243925842183L;
    private int id;
    /**
     * 父权限
     */
    private int parentId;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限英文名称
     */
    private String enname;
    /**
     * 授权路径
     */
    private String url;
    /**
     * 备注
     */
    private String description;
    private Date created;
    private Date updated;
    /**
     * 小程序id
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



    public DbPermission() {
    }
}