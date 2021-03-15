package com.umpay.rms.gpd.user.mapper;




import com.umpay.rms.gpd.user.api.entity.DbButton;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface DbButtonMapper {

    DbButton getById(Integer id);

    List<DbButton> listByEntity(DbButton dbButton);

    DbButton getByEntity(DbButton dbButton);

    List<DbButton> listByIds(List<DbButton> list);

    int insert(DbButton dbButton);

    int insertBatch( List<DbButton> list);

    int update(DbButton dbButton);

    int updateByField(@Param("where") DbButton where, @Param("set") DbButton set);

    int updateBatch( List<DbButton> list);

    int deleteById( Integer id  );

    int deleteByEntity(DbButton dbButton);
  
    int deleteByIds( List<DbButton> list);
    
    int countAll();
    
    int countByEntity(DbButton dbButton);

    Set<String> selectButtonByUserId(Integer id);
}