package com.umpay.rms.gpd.user.api.entity;

import java.io.Serializable;

/**
 * (DbPermissionButton)实体类
 *
 * @author xiaoG
 * @since 2020-06-29 14:27:07
 */
public class DbPermissionButton  implements Serializable {

    private Integer id;
    /**
     * 按钮id
     */
    private Integer buttonId;
    /**
     * 权限id
     */
    private Integer permissionId;
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

    public Integer getButtonId() {
        return buttonId;
    }

    public void setButtonId(Integer buttonId) {
        this.buttonId = buttonId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }
}