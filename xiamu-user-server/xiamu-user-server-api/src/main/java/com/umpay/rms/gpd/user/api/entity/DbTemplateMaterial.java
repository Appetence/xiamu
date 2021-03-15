package com.umpay.rms.gpd.user.api.entity;

import java.io.Serializable;

/**
 * (DbTemplateMaterial)实体类
 *
 * @author makejava
 * @since 2020-06-09 18:37:57
 */
public class DbTemplateMaterial implements Serializable {
    private static final long serialVersionUID = 371147350217553860L;
    
    private Integer id;
    
    private String materialId;
    
    private String templateId;
    
    private Integer materialOrder;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Integer getMaterialOrder() {
        return materialOrder;
    }

    public void setMaterialOrder(Integer materialOrder) {
        this.materialOrder = materialOrder;
    }
}