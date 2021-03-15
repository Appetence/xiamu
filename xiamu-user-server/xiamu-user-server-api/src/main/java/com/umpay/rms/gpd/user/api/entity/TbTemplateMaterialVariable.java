package com.umpay.rms.gpd.user.api.entity;

//import lombok.Data;

import io.swagger.annotations.ApiModelProperty;

/**
 * (TbTemplateMaterialVariable)实体类
 *
 * @author xiaoG
 * @since 2020-10-14 14:55:53
 */
//@Data 
public class TbTemplateMaterialVariable {
    @ApiModelProperty(value = "主键")

    private Integer id;
    /**
     * 模版id
     */
    @ApiModelProperty(value = "模版id")

    private String templateId;
    /**
     * 素材id
     */
    @ApiModelProperty(value = "素材id")

    private String materialId;
    /**
     * 变量id
     */
    @ApiModelProperty(value = "变量id")

    private String varId;
    /**
     * 顺序
     */
    @ApiModelProperty(value = "顺序编号")

    private Integer serial ;
    /**
     * 状态：1 有效，2 无效
     */
    @ApiModelProperty(value = "状态：1 有效，2 无效")

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getVarId() {
        return varId;
    }

    public void setVarId(String varId) {
        this.varId = varId;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}