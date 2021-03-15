package com.umpay.rms.gpd.user.service.impl;


import com.umpay.rms.gpd.user.api.entity.TbAccountCodeNumber;
import com.umpay.rms.gpd.user.mapper.TbAccountCodeNumberMapper;
import com.umpay.rms.gpd.user.service.TbAccountCodeNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbAccountCodeNumber)表服务实现类
 *
 * @author makejava
 * @since 2020-06-14 10:50:02
 */
@Service("tbAccountCodeNumberService")
public class TbAccountCodeNumberServiceImpl implements TbAccountCodeNumberService {
    Logger logger = LoggerFactory.getLogger( this.getClass() );
    @Resource
    private TbAccountCodeNumberMapper tbAccountCodeNumberDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public TbAccountCodeNumber queryById(Integer id) {
        return this.tbAccountCodeNumberDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<TbAccountCodeNumber> queryAllByLimit(int offset, int limit) {
        return this.tbAccountCodeNumberDao.queryAllByLimit(offset, limit);
    }
  /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<TbAccountCodeNumber> queryAll(TbAccountCodeNumber tbAccountCodeNumber) {
        return this.tbAccountCodeNumberDao.queryAll(tbAccountCodeNumber);
    }
    /**
     * 新增数据
     *
     * @param tbAccountCodeNumber 实例对象
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public TbAccountCodeNumber insert(TbAccountCodeNumber tbAccountCodeNumber) {
        this.tbAccountCodeNumberDao.insert(tbAccountCodeNumber);
        return tbAccountCodeNumber;
    }

    /**
     * 修改数据
     *
     * @param tbAccountCodeNumber 实例对象
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public TbAccountCodeNumber update(TbAccountCodeNumber tbAccountCodeNumber) {
        this.tbAccountCodeNumberDao.update(tbAccountCodeNumber);
        return this.queryById(tbAccountCodeNumber.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public boolean deleteById(Integer id) {
        return this.tbAccountCodeNumberDao.deleteById(id) > 0;
    }

    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Boolean checkCodeNumber(TbAccountCodeNumber tbAccountCodeNumber) {
        TbAccountCodeNumber tbAccountCodeNumber1 = new TbAccountCodeNumber();
        tbAccountCodeNumber1.setCodeNumber(tbAccountCodeNumber.getCodeNumber());
        //TODO   不判断当前用户
        List<TbAccountCodeNumber> list = this.tbAccountCodeNumberDao.checkCodeNumber(tbAccountCodeNumber);
        if(CollectionUtils.isEmpty(list)){
            return true;//不重复
        }else {
            return false;//重复
        }
    }

    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int insertCodeNumber(List<TbAccountCodeNumber> codeNumber) {
        return this.tbAccountCodeNumberDao.insertCodeNumber(codeNumber);
    }



    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int deleteByAccountId(String accountId) {
        return this.tbAccountCodeNumberDao.deleteByAccountId(accountId);
    }

    @Override
    public int statisticalVmsCode(String vmsCode, String ciId) {
        return tbAccountCodeNumberDao.statisticalVmsCode(vmsCode, ciId);
    }
}