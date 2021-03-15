package com.umpay.rms.gpd.user.service;




import com.umpay.rms.gpd.user.api.entity.DbExaminationApproval;

import java.util.List;

/**
 * (DbExaminationApproval)表服务接口
 *
 * @author makejava
 * @since 2020-06-08 14:42:40
 */
public interface DbExaminationApprovalService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DbExaminationApproval queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbExaminationApproval> queryAllByLimit(int offset, int limit);
    /**
     * 查询全部数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbExaminationApproval> queryAll(DbExaminationApproval dbExaminationApproval);
    /**
     * 新增数据
     *
     * @param dbExaminationApproval 实例对象
     * @return 实例对象
     */
    DbExaminationApproval insert(DbExaminationApproval dbExaminationApproval);

    /**
     * 修改数据
     *
     * @param dbExaminationApproval 实例对象
     * @return 实例对象
     */
    DbExaminationApproval update(DbExaminationApproval dbExaminationApproval);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}