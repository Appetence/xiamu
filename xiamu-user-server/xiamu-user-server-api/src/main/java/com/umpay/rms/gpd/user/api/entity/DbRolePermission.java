package com.umpay.rms.gpd.user.api.entity;



import java.io.Serializable;

/**
 * 角色权限表(DbRolePermission)实体类
 *
 * @author makejava
 * @since 2020-05-10 18:55:25
 */

public class DbRolePermission implements Serializable {
    private static final long serialVersionUID = -64124017240626037L;
    private int id;
    /**
     * 角色 ID
     */
    private int roleId;
    /**
     * 权限 ID
     */
    private int permissionId;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}