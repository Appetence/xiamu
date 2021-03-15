package com.umpay.rms.gpd.user.service.impl;



import com.umpay.rms.gpd.user.api.entity.TbAccountBalance;
import com.umpay.rms.gpd.user.api.entity.TbAccountRecord;
import com.umpay.rms.gpd.user.mapper.TbAccountRecordMapper;
import com.umpay.rms.gpd.user.service.TbAccountRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbAccountRecord)表服务实现类
 *
 * @author makejava
 * @since 2020-06-14 22:37:18
 */
@Service("tbAccountRecordService")
public class TbAccountRecordServiceImpl implements TbAccountRecordService {
    Logger logger = LoggerFactory.getLogger( this.getClass() );
    @Resource
    private TbAccountRecordMapper tbAccountRecordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public TbAccountRecord queryById(Integer id) {
        return this.tbAccountRecordDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<TbAccountRecord> queryAllByLimit(int offset, int limit) {
        return this.tbAccountRecordDao.queryAllByLimit(offset, limit);
    }
  /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<TbAccountRecord> queryAll(TbAccountRecord tbAccountRecord) {
        return this.tbAccountRecordDao.queryAll(tbAccountRecord);
    }
    /**
     * 新增数据
     *
     * @param tbAccountRecord 实例对象
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public TbAccountRecord insert(TbAccountRecord tbAccountRecord) {
        this.tbAccountRecordDao.insert(tbAccountRecord);
        return tbAccountRecord;
    }

    /**
     * 修改数据
     *
     * @param tbAccountRecord 实例对象
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public TbAccountRecord update(TbAccountRecord tbAccountRecord) {
        this.tbAccountRecordDao.update(tbAccountRecord);
        return this.queryById(tbAccountRecord.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public boolean deleteById(Integer id) {
        return this.tbAccountRecordDao.deleteById(id) > 0;
    }

    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<TbAccountRecord> selectSunAccountRecord(TbAccountRecord tbAccountRecord) {
        return this.tbAccountRecordDao.selectSunAccountRecord(tbAccountRecord) ;
    }

    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<TbAccountRecord> selectDelegateAccountRechangeRecord(TbAccountRecord tbAccountRecord) {
        return this.tbAccountRecordDao.selectDelegateAccountRechangeRecord(tbAccountRecord);
       // return this.tbAccountRecordDao.selectSunAccountRecord;
    }

    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public TbAccountBalance countAccountWeekRechangeNumber(TbAccountBalance  tbAccountBalance) {
        return this.tbAccountRecordDao.countAccountWeekRechangeNumber(tbAccountBalance);

    }
}