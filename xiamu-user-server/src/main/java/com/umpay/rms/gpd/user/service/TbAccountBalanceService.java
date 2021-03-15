package com.umpay.rms.gpd.user.service;




import com.umpay.rms.gpd.user.api.entity.TbAccountBalance;

import java.util.List;
import java.util.Vector;

/**
 * (TbAccountBalance)表服务接口
 *
 * @author makejava
 * @since 2020-06-14 22:37:14
 */
public interface TbAccountBalanceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbAccountBalance queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbAccountBalance> queryAllByLimit(int offset, int limit);
    /**
     * 查询全部数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbAccountBalance> queryAll(TbAccountBalance tbAccountBalance);
    /**
     * 新增数据
     *
     * @param tbAccountBalance 实例对象
     * @return 实例对象
     */
    TbAccountBalance insert(TbAccountBalance tbAccountBalance);

    /**
     * 修改数据
     *
     * @param tbAccountBalance 实例对象
     * @return 实例对象
     */
    int update(TbAccountBalance tbAccountBalance);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Long countBalanceBySunAccount(TbAccountBalance tbAccountBalance);

    Long countSendNumberBySunAccount(TbAccountBalance tbAccountBalance);


    Long countDelegateAccountWeekRechange(TbAccountBalance tbAccountBalance);

    Long countDelegateAccountSumBalance(TbAccountBalance tbAccountBalance);

    //批量修改账户
     int batchUpdateAccount(Vector<TbAccountBalance> tbAccounts);
}