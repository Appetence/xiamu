package com.umpay.rms.gpd.user.mapper;



import com.umpay.rms.gpd.user.api.entity.DbExaminationApproval;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (DbExaminationApproval)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-08 14:42:40
 */
public interface DbExaminationApprovalMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DbExaminationApproval queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbExaminationApproval> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

/**
     * 查询指所有
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbExaminationApproval> queryAll();
    /**
     * 通过实体作为筛选条件查询
     *
     * @param dbExaminationApproval 实例对象
     * @return 对象列表
     */
    List<DbExaminationApproval> queryAll(DbExaminationApproval dbExaminationApproval);

    /**
     * 新增数据
     *
     * @param dbExaminationApproval 实例对象
     * @return 影响行数
     */
    int insert(DbExaminationApproval dbExaminationApproval);

    /**
     * 修改数据
     *
     * @param dbExaminationApproval 实例对象
     * @return 影响行数
     */
    int update(DbExaminationApproval dbExaminationApproval);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}