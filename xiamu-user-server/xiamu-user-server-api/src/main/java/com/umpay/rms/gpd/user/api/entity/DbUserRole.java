package com.umpay.rms.gpd.user.api.entity;



import java.io.Serializable;

/**
 * 用户角色表(DbUserRole)实体类
 *
 * @author makejava
 * @since 2020-05-10 18:55:26
 */


public class DbUserRole implements Serializable {
    private static final long serialVersionUID = -96181468247471112L;
    private int id;
    /**
     * 用户 ID
     */
    private int userId;
    /**
     * 角色 ID
     */
    private int roleId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}