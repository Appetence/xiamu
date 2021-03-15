package com.umpay.rms.gpd.user.mapper;


import com.umpay.rms.gpd.user.api.entity.DbUserLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (DbUserLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-05 13:20:59
 */
public interface DbUserLogMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DbUserLog queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbUserLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

/**
     * 查询指所有
     * @return 对象列表
     */
    List<DbUserLog> queryAll();
    /**
     * 通过实体作为筛选条件查询
     *
     * @param dbUserLog 实例对象
     * @return 对象列表
     */
    List<DbUserLog> queryAll(DbUserLog dbUserLog);

    /**
     * 新增数据
     *
     * @param dbUserLog 实例对象
     * @return 影响行数
     */
    int insert(DbUserLog dbUserLog);

    /**
     * 修改数据
     *
     * @param dbUserLog 实例对象
     * @return 影响行数
     */
    int update(DbUserLog dbUserLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}