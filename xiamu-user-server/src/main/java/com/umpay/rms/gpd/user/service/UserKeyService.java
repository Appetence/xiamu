package com.umpay.rms.gpd.user.service;


import com.umpay.rms.gpd.internal.dto.Msg;

/**
 * @program: rms-gpd
 * @description: 公私钥
 * @author: 山水迢迢
 * @create: 2020-07-24 13:58
 */

public interface UserKeyService {

    public Msg createKey(String jensionId);

    public Msg getKey(String jensionId);

}
