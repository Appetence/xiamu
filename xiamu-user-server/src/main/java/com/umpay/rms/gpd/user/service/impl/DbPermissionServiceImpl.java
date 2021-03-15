package com.umpay.rms.gpd.user.service.impl;


import com.umpay.rms.gpd.user.api.entity.DbPermission;
import com.umpay.rms.gpd.user.mapper.DbPermissionMapper;
import com.umpay.rms.gpd.user.service.DbPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表(DbPermission)表服务实现类
 *
 * @author makejava
 * @since 2020-06-02 14:51:19
 */
@Service("PermissionService")
public class DbPermissionServiceImpl implements DbPermissionService {
    Logger logger = LoggerFactory.getLogger( this.getClass() );
    @Resource
    private DbPermissionMapper dbPermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbPermission queryById(Long id) {
        return this.dbPermissionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbPermission> queryAllByLimit(int offset, int limit) {
        return this.dbPermissionDao.queryAllByLimit(offset, limit);
    }
  /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbPermission> queryAll(DbPermission dbPermission) {
        return this.dbPermissionDao.queryAll(dbPermission);
    }
    /**
     * 新增数据
     *
     * @param dbPermission 实例对象
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbPermission insert(DbPermission dbPermission) {
        this.dbPermissionDao.insert(dbPermission);
        return dbPermission;
    }

    /**
     * 修改数据
     *
     * @param dbPermission 实例对象
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbPermission update(DbPermission dbPermission) {
        this.dbPermissionDao.update(dbPermission);
        return this.queryById(Long.parseLong(dbPermission.getId()+""));
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public boolean deleteById(Long id) {
        return this.dbPermissionDao.deleteById(id) > 0;
    }
}