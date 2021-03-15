package com.umpay.rms.gpd.user.service;
import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.user.api.entity.DbRole;
import java.util.List;

/**
 * 角色表(DbRole)表服务接口
 *
 * @author makejava
 * @since 2020-06-02 14:51:08
 */
public interface DbRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DbRole queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbRole> queryAllByLimit(int offset, int limit);
    /**
     * 查询全部数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbRole> queryAll(DbRole dbRole);
    /**
     * 新增数据
     *
     * @param dbRole 实例对象
     * @return 实例对象
     */
    DbRole insert(DbRole dbRole);

    /**
     * 修改数据
     *
     * @param dbRole 实例对象
     * @return 实例对象
     */
    Msg update(DbRole dbRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}