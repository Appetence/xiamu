package com.umpay.rms.gpd.user.mapper;

import com.umpay.rms.gpd.user.api.entity.DbPermissionButton;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DbPermissionButtonMapper {

    DbPermissionButton getById(Integer id);

    List<DbPermissionButton> listByEntity(DbPermissionButton dbPermissionButton);

    DbPermissionButton getByEntity(DbPermissionButton dbPermissionButton);

    List<DbPermissionButton> listByIds(List<Integer> list);

    int insert( DbPermissionButton dbPermissionButton);

    int insertBatch( List<DbPermissionButton> list);

    int update( DbPermissionButton dbPermissionButton);

    int updateByField(@Param("where") DbPermissionButton where, @Param("set") DbPermissionButton set);

    int updateBatch( List<DbPermissionButton> list);

    int deleteById( Integer id);

    int deleteByEntity( DbPermissionButton dbPermissionButton);
  
    int deleteByIds( List<Integer> list);
    
    int countAll();
    
    int countByEntity(DbPermissionButton dbPermissionButton);
    
}