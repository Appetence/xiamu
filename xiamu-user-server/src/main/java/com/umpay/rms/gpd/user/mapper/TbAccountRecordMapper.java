package com.umpay.rms.gpd.user.mapper;




import com.umpay.rms.gpd.user.api.entity.TbAccountBalance;
import com.umpay.rms.gpd.user.api.entity.TbAccountRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TbAccountRecord)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-14 22:37:16
 */
public interface TbAccountRecordMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbAccountRecord queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbAccountRecord> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

/**
     * 查询指所有
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbAccountRecord> queryAll();
    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbAccountRecord 实例对象
     * @return 对象列表
     */
    List<TbAccountRecord> queryAll(TbAccountRecord tbAccountRecord);

    /**
     * 新增数据
     *
     * @param tbAccountRecord 实例对象
     * @return 影响行数
     */
    int insert(TbAccountRecord tbAccountRecord);

    /**
     * 修改数据
     *
     * @param tbAccountRecord 实例对象
     * @return 影响行数
     */
    int update(TbAccountRecord tbAccountRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<TbAccountRecord> selectSunAccountRecord(TbAccountRecord tbAccountRecord);

    List<TbAccountRecord> selectDelegateAccountRechangeRecord(TbAccountRecord tbAccountRecord);

    TbAccountBalance countAccountWeekRechangeNumber(TbAccountBalance tbAccountBalance);
}