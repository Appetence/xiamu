package com.umpay.rms.gpd.user.mapper;


import com.umpay.rms.gpd.user.api.entity.DbRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色表(DbRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-02 15:15:55
 */
public interface DbRoleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DbRole queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbRole> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询指所有
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbRole> queryAll();
    /**
     * 通过实体作为筛选条件查询
     *
     * @param dbRole 实例对象
     * @return 对象列表
     */
    List<DbRole> queryAll(DbRole dbRole);

    /**
     * 新增数据
     *
     * @param dbRole 实例对象
     * @return 影响行数
     */
    int insert(DbRole dbRole);

    /**
     * 修改数据
     *
     * @param dbRole 实例对象
     * @return 影响行数
     */
    int update(DbRole dbRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}