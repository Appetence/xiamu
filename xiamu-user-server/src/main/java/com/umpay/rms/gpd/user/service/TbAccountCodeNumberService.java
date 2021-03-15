package com.umpay.rms.gpd.user.service;




import com.umpay.rms.gpd.user.api.entity.TbAccountCodeNumber;

import java.util.List;

/**
 * (TbAccountCodeNumber)表服务接口
 *
 * @author makejava
 * @since 2020-06-14 10:50:02
 */
public interface TbAccountCodeNumberService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbAccountCodeNumber queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbAccountCodeNumber> queryAllByLimit(int offset, int limit);
    /**
     * 查询全部数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbAccountCodeNumber> queryAll(TbAccountCodeNumber tbAccountCodeNumber);
    /**
     * 新增数据
     *
     * @param tbAccountCodeNumber 实例对象
     * @return 实例对象
     */
    TbAccountCodeNumber insert(TbAccountCodeNumber tbAccountCodeNumber);

    /**
     * 修改数据
     *
     * @param tbAccountCodeNumber 实例对象
     * @return 实例对象
     */
    TbAccountCodeNumber update(TbAccountCodeNumber tbAccountCodeNumber);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Boolean checkCodeNumber(TbAccountCodeNumber tbAccountCodeNumber);

    int  insertCodeNumber(List<TbAccountCodeNumber> codeNumber);

    int deleteByAccountId(String accountId);

    int statisticalVmsCode(String vmsCode, String ciId);

}