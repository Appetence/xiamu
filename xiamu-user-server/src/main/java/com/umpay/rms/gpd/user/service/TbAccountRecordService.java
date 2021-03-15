package com.umpay.rms.gpd.user.service;





import com.umpay.rms.gpd.user.api.entity.TbAccountBalance;
import com.umpay.rms.gpd.user.api.entity.TbAccountRecord;

import java.util.List;

/**
 * (TbAccountRecord)表服务接口
 *
 * @author makejava
 * @since 2020-06-14 22:37:17
 */
public interface TbAccountRecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbAccountRecord queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbAccountRecord> queryAllByLimit(int offset, int limit);
    /**
     * 查询全部数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbAccountRecord> queryAll(TbAccountRecord tbAccountRecord);
    /**
     * 新增数据
     *
     * @param tbAccountRecord 实例对象
     * @return 实例对象
     */
    TbAccountRecord insert(TbAccountRecord tbAccountRecord);

    /**
     * 修改数据
     *
     * @param tbAccountRecord 实例对象
     * @return 实例对象
     */
    TbAccountRecord update(TbAccountRecord tbAccountRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<TbAccountRecord> selectSunAccountRecord(TbAccountRecord tbAccountRecord);

    List<TbAccountRecord> selectDelegateAccountRechangeRecord(TbAccountRecord tbAccountRecord);

    TbAccountBalance countAccountWeekRechangeNumber(TbAccountBalance tbAccountBalance);
}