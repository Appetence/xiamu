package com.umpay.rms.gpd.user.mapper;



import com.umpay.rms.gpd.user.api.entity.DbPermission;
import com.umpay.rms.gpd.user.api.entity.DbRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限表(DbRolePermission)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-02 15:15:58
 */
public interface DbRolePermissionMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DbRolePermission queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbRolePermission> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询指所有
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbRolePermission> queryAll();
    /**
     * 通过实体作为筛选条件查询
     *
     * @param dbRolePermission 实例对象
     * @return 对象列表
     */
    List<DbRolePermission> queryAll(DbRolePermission dbRolePermission);

    /**
     * 新增数据
     *
     * @param dbRolePermission 实例对象
     * @return 影响行数
     */
    int insert(DbRolePermission dbRolePermission);

    /**
     * 修改数据
     *
     * @param dbRolePermission 实例对象
     * @return 影响行数
     */
    int update(DbRolePermission dbRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<DbPermission> selectRolePermission(Integer userId);
}