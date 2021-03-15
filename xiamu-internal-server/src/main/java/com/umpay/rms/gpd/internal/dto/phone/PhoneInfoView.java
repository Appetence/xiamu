package com.umpay.rms.gpd.internal.dto.phone;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @date 2020/8/29
 **/
@Data
@Accessors(chain = true)
public class PhoneInfoView  {
    private int id;

    private String phone;

    private String encrypt;

}
