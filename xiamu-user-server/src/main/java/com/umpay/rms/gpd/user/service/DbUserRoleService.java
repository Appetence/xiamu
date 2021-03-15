package com.umpay.rms.gpd.user.service;





import com.umpay.rms.gpd.user.api.entity.DbRole;
import com.umpay.rms.gpd.user.api.entity.DbUserRole;

import java.util.List;

/**
 * 用户角色表(DbUserRole)表服务接口
 *
 * @author makejava
 * @since 2020-06-02 14:45:40
 */
public interface DbUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DbUserRole queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbUserRole> queryAllByLimit(int offset, int limit);
    /**
     * 查询全部数据
     *
     * @return 对象列表
     */
    List<DbUserRole> queryAll(DbUserRole dbUserRole);
    /**
     * 新增数据
     *
     * @param dbUserRole 实例对象
     * @return 实例对象
     */
    DbUserRole insert(DbUserRole dbUserRole);

    /**
     * 修改数据
     *
     * @param dbUserRole 实例对象
     * @return 实例对象
     */
    DbUserRole update(DbUserRole dbUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<DbRole> selectUserRole(DbUserRole dbUserRole);

    int updateUserRole(List<DbUserRole> userRoleList);
}