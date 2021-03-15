package com.umpay.rms.gpd.user.api.entity;

import java.io.Serializable;

/**
 * (TbBusiness)实体类
 *
 * @author makejava
 * @since 2020-06-18 16:01:53
 */
public class TbBusiness implements Serializable {
    private static final long serialVersionUID = -29389773666923928L;
    
    private Integer businessId;
    
    private String businessCode;
    
    private String businessName;


    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

}