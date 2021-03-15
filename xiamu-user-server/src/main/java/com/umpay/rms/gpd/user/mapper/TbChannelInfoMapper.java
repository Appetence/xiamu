package com.umpay.rms.gpd.user.mapper;


import com.umpay.rms.gpd.user.api.entity.TbChannelInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TbChannelInfoMapper {
    /**
     * 根据默认状态查看通道信息
     * @param isDefault
     * @return
     */
    List<TbChannelInfo> selectDefaultChannelInfo(@Param("isDefault") Integer isDefault);

}