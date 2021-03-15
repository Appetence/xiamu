package com.umpay.rms.gpd.user.service;




import com.umpay.rms.gpd.user.api.entity.DbPermission;

import java.util.List;

/**
 * 权限表(DbPermission)表服务接口
 *
 * @author makejava
 * @since 2020-06-02 14:51:19
 */
public interface DbPermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DbPermission queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbPermission> queryAllByLimit(int offset, int limit);
    /**
     * 查询全部数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbPermission> queryAll(DbPermission dbPermission);
    /**
     * 新增数据
     *
     * @param dbPermission 实例对象
     * @return 实例对象
     */
    DbPermission insert(DbPermission dbPermission);

    /**
     * 修改数据
     *
     * @param dbPermission 实例对象
     * @return 实例对象
     */
    DbPermission update(DbPermission dbPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}