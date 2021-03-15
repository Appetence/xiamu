package com.umpay.rms.gpd.user.service;



import com.umpay.rms.gpd.user.api.entity.DbUserLog;

import java.util.List;

/**
 * (DbUserLog)表服务接口
 *
 * @author makejava
 * @since 2020-06-05 13:21:25
 */
public interface DbUserLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DbUserLog queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbUserLog> queryAllByLimit(int offset, int limit);
    /**
     * 查询全部数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbUserLog> queryAll(DbUserLog dbUserLog);
    /**
     * 新增数据
     *
     * @param dbUserLog 实例对象
     * @return 实例对象
     */
    DbUserLog insert(DbUserLog dbUserLog);

    /**
     * 修改数据
     *
     * @param dbUserLog 实例对象
     * @return 实例对象
     */
    DbUserLog update(DbUserLog dbUserLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}