package com.umpay.rms.gpd.user.service;



import com.umpay.rms.gpd.user.api.entity.DbButton;
import com.umpay.rms.gpd.user.mapper.DbButtonMapper;

import java.util.List;
import java.util.Set;

public interface DbButtonService {
    
    DbButtonMapper getDbButtonDao();
   
    DbButton getById(Integer id );

    DbButton getByEntity(DbButton dbButton);

    List<DbButton> listByEntity(DbButton dbButton);

    List<DbButton> listByIds(List<DbButton> ids);

    int insert(DbButton dbButton);

    int insertBatch(List<DbButton> list);

    int update(DbButton dbButton);

    int updateBatch(List<DbButton> list);

    int deleteById(Integer id );

    int deleteByEntity(DbButton dbButton);
  
    int deleteByIds(List<DbButton> list);
    
    int countAll();
    
    int countByEntity(DbButton dbButton);


    Set<String> selectButtonByUserId(Integer id);

}