package com.umpay.rms.gpd.user.service.impl;


import com.umpay.rms.gpd.user.api.entity.DbUserLog;
import com.umpay.rms.gpd.user.mapper.DbUserLogMapper;
import com.umpay.rms.gpd.user.service.DbUserLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DbUserLog)表服务实现类
 *
 * @author makejava
 * @since 2020-06-05 13:21:03
 */
@Service("dbUserLogService")
public class DbUserLogServiceImpl implements DbUserLogService {
    Logger logger = LoggerFactory.getLogger( this.getClass() );
    @Resource
    private DbUserLogMapper dbUserLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbUserLog queryById(Integer id) {
        return this.dbUserLogDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbUserLog> queryAllByLimit(int offset, int limit) {
        return this.dbUserLogDao.queryAllByLimit(offset, limit);
    }
  /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbUserLog> queryAll(DbUserLog dbUserLog) {
        return this.dbUserLogDao.queryAll(dbUserLog);
    }
    /**
     * 新增数据
     *
     * @param dbUserLog 实例对象
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbUserLog insert(DbUserLog dbUserLog) {
        this.dbUserLogDao.insert(dbUserLog);
        return dbUserLog;
    }

    /**
     * 修改数据
     *
     * @param dbUserLog 实例对象
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbUserLog update(DbUserLog dbUserLog) {
        this.dbUserLogDao.update(dbUserLog);
        return this.queryById(dbUserLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public boolean deleteById(Integer id) {
        return this.dbUserLogDao.deleteById(id) > 0;
    }
}