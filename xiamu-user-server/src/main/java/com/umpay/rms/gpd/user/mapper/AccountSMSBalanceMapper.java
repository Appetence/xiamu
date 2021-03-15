package com.umpay.rms.gpd.user.mapper;


import com.umpay.rms.gpd.user.api.entity.TbAccountBalance;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Vector;

/**
 * (TbAccountBalance)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-14 22:37:13
 */
public interface AccountSMSBalanceMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbAccountBalance queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbAccountBalance> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

/**
     * 查询指所有
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbAccountBalance> queryAll();
    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbAccountBalance 实例对象
     * @return 对象列表
     */
    List<TbAccountBalance> queryAll(TbAccountBalance tbAccountBalance);

    /**
     * 新增数据
     *
     * @param tbAccountBalance 实例对象
     * @return 影响行数
     */
    int insert(TbAccountBalance tbAccountBalance);

    /**
     * 修改数据
     *
     * @param tbAccountBalance 实例对象
     * @return 影响行数
     */
    int update(TbAccountBalance tbAccountBalance);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    Long countBalanceBySunAccount(TbAccountBalance tbAccountBalance);

    Long countSendNumberBySunAccount(TbAccountBalance tbAccountBalance);

    int batchUpdateAccount(Vector<TbAccountBalance> tbAccounts);

    Long countDelegateAccountSumBalanceAndWeekRechange(TbAccountBalance tbAccountBalance);

    Long countDelegateAccountWeekRechange(TbAccountBalance tbAccountBalance);

    Long countDelegateAccountSumBalance(TbAccountBalance tbAccountBalance);
}