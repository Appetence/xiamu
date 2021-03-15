package com.umpay.rms.gpd.user.service.impl;


import com.umpay.rms.gpd.user.api.entity.DbExaminationApproval;
import com.umpay.rms.gpd.user.mapper.DbExaminationApprovalMapper;
import com.umpay.rms.gpd.user.service.DbExaminationApprovalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DbExaminationApproval)表服务实现类
 *
 * @author makejava
 * @since 2020-06-08 14:42:42
 */
@Service("dbExaminationApprovalService")
public class DbExaminationApprovalServiceImpl implements DbExaminationApprovalService {
    Logger logger = LoggerFactory.getLogger( this.getClass() );
    @Resource
    private DbExaminationApprovalMapper dbExaminationApprovalDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbExaminationApproval queryById(Integer id) {
        return this.dbExaminationApprovalDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbExaminationApproval> queryAllByLimit(int offset, int limit) {
        return this.dbExaminationApprovalDao.queryAllByLimit(offset, limit);
    }
  /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbExaminationApproval> queryAll(DbExaminationApproval dbExaminationApproval) {
        return this.dbExaminationApprovalDao.queryAll(dbExaminationApproval);
    }
    /**
     * 新增数据
     *
     * @param dbExaminationApproval 实例对象
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbExaminationApproval insert(DbExaminationApproval dbExaminationApproval) {
        this.dbExaminationApprovalDao.insert(dbExaminationApproval);
        return dbExaminationApproval;
    }

    /**
     * 修改数据
     *
     * @param dbExaminationApproval 实例对象
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbExaminationApproval update(DbExaminationApproval dbExaminationApproval) {
        this.dbExaminationApprovalDao.update(dbExaminationApproval);
        return this.queryById(dbExaminationApproval.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public boolean deleteById(Integer id) {
        return this.dbExaminationApprovalDao.deleteById(id) > 0;
    }
}