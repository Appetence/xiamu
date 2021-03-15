package com.umpay.rms.gpd.user.mapper;



import com.umpay.rms.gpd.user.api.entity.DbRole;
import com.umpay.rms.gpd.user.api.entity.DbUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色表(DbUserRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-02 14:45:40
 */
public interface DbUserRoleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DbUserRole queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbUserRole> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

/**
     * 查询指所有
     *
     * @return 对象列表
     */
    List<DbUserRole> queryAll();
    /**
     * 通过实体作为筛选条件查询
     *
     * @param dbUserRole 实例对象
     * @return 对象列表
     */
    List<DbUserRole> queryAll(DbUserRole dbUserRole);

    /**
     * 新增数据
     *
     * @param dbUserRole 实例对象
     * @return 影响行数
     */
    int insert(DbUserRole dbUserRole);

    /**
     * 修改数据
     *
     * @param dbUserRole 实例对象
     * @return 影响行数
     */
    int update(DbUserRole dbUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<DbRole> selectUserRole(DbUserRole dbUserRole);

    int updateUserRole(List<DbUserRole> userRoleList);
}