package com.umpay.rms.gpd.user.service.impl;


import com.umpay.rms.gpd.user.api.entity.TbAccountBalance;
import com.umpay.rms.gpd.user.mapper.TbAccountBalanceMapper;
import com.umpay.rms.gpd.user.service.TbAccountBalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Vector;

/**
 * (TbAccountBalance)表服务实现类
 *
 * @author makejava
 * @since 2020-06-14 22:37:14
 */

@Service("tbAccountBalanceService")
public class TbAccountBalanceServiceImpl implements TbAccountBalanceService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TbAccountBalanceMapper tbAccountBalanceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public TbAccountBalance queryById(Integer id) {
        return this.tbAccountBalanceDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<TbAccountBalance> queryAllByLimit(int offset, int limit) {
        return this.tbAccountBalanceDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<TbAccountBalance> queryAll(TbAccountBalance tbAccountBalance) {
        return this.tbAccountBalanceDao.queryAll(tbAccountBalance);
    }

    /**
     * 新增数据
     *
     * @param tbAccountBalance 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public TbAccountBalance insert(TbAccountBalance tbAccountBalance) {
        this.tbAccountBalanceDao.insert(tbAccountBalance);
        return tbAccountBalance;
    }

    /**
     * 修改数据
     *
     * @param tbAccountBalance 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int update(TbAccountBalance tbAccountBalance) {
        return this.tbAccountBalanceDao.update(tbAccountBalance);

    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public boolean deleteById(Integer id) {
        return this.tbAccountBalanceDao.deleteById(id) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Long countBalanceBySunAccount(TbAccountBalance tbAccountBalance) {


        return tbAccountBalanceDao.countBalanceBySunAccount(tbAccountBalance);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Long countSendNumberBySunAccount(TbAccountBalance tbAccountBalance) {
        return tbAccountBalanceDao.countSendNumberBySunAccount(tbAccountBalance);

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Long countDelegateAccountWeekRechange(TbAccountBalance tbAccountBalance) {
        return tbAccountBalanceDao.countDelegateAccountWeekRechange(tbAccountBalance);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Long countDelegateAccountSumBalance(TbAccountBalance tbAccountBalance) {
        return tbAccountBalanceDao.countDelegateAccountSumBalance(tbAccountBalance);
    }

    @Override
    public int batchUpdateAccount(Vector<TbAccountBalance> tbAccounts){
        return tbAccountBalanceDao.batchUpdateAccount(tbAccounts);
    }


}