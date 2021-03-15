package com.umpay.rms.gpd.user.api.entity;

//import lombok.Data;

import io.swagger.annotations.ApiModelProperty;

/**
 * (TbVariable)实体类
 *
 * @author xiaoG
 * @since 2020-10-14 14:28:58
 */
public class TbVariable {
    @ApiModelProperty(value = "主键")

    private Integer id;
    /**
     * 名称
     */
    @ApiModelProperty(value = " 名称")

    private String name;
    /**
     * 长度
     */
    @ApiModelProperty(value = "长度")

    private Integer length;
    /**
     * 规则id
     */
    @ApiModelProperty(value = "规则id")

    private Integer variableRoleId;
    /**
     * 状态： 1 有效 2 无效
     */
    @ApiModelProperty(value = " 状态： 1 有效 2 无效")

    private Integer status;

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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getVariableRoleId() {
        return variableRoleId;
    }

    public void setVariableRoleId(Integer variableRoleId) {
        this.variableRoleId = variableRoleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}