package com.umpay.rms.gpd.internal.dto.phone;

import lombok.Data;

import java.util.List;

/**
 * 电话号码加密解密DTO
 *
 * @date 2020/8/29
 */
@Data
public class PhoneInfoDto {
    private Integer idType;
    private List<PhoneInfoView> infoList;
}
