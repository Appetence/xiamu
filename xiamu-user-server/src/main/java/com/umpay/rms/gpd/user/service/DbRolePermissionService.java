package com.umpay.rms.gpd.user.service;






import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.user.api.entity.DbPermission;
import com.umpay.rms.gpd.user.api.entity.DbRolePermission;

import java.util.List;

/**
 * 角色权限表(DbRolePermission)表服务接口
 *
 * @author makejava
 * @since 2020-06-02 15:10:17
 */
public interface DbRolePermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DbRolePermission queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbRolePermission> queryAllByLimit(int offset, int limit);
    /**
     * 查询全部数据
     *
     * @return 对象列表
     */
    List<DbRolePermission> queryAll(DbRolePermission dbRolePermission);
    /**
     * 新增数据
     *
     * @param dbRolePermission 实例对象
     * @return 实例对象
     */
    DbRolePermission insert(DbRolePermission dbRolePermission);

    /**
     * 修改数据
     *
     * @param dbRolePermission 实例对象
     * @return 实例对象
     */
    Msg update(DbRolePermission dbRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<DbPermission> selectRolePermission(Integer userId);
}