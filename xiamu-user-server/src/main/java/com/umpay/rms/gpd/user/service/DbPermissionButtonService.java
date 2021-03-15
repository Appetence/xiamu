package com.umpay.rms.gpd.user.service;


import com.umpay.rms.gpd.user.api.entity.DbPermissionButton;
import com.umpay.rms.gpd.user.mapper.DbPermissionButtonMapper;

import java.util.List;

public interface DbPermissionButtonService {
    
    DbPermissionButtonMapper getDbPermissionButtonDao();
   
    DbPermissionButton getById(Integer id);

    DbPermissionButton getByEntity(DbPermissionButton dbPermissionButton);

    List<DbPermissionButton> listByEntity(DbPermissionButton dbPermissionButton);

    List<DbPermissionButton> listByIds(List<Integer> ids);

    int insert(DbPermissionButton dbPermissionButton);

    int insertBatch(List<DbPermissionButton> list);

    int update(DbPermissionButton dbPermissionButton);

    int updateBatch(List<DbPermissionButton> list);

    int deleteById(Integer id);

    int deleteByEntity(DbPermissionButton dbPermissionButton);
  
    int deleteByIds(List<Integer> list);
    
    int countAll();
    
    int countByEntity(DbPermissionButton dbPermissionButton);
}