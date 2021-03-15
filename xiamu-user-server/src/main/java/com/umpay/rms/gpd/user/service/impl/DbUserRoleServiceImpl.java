package com.umpay.rms.gpd.user.service.impl;




import com.umpay.rms.gpd.user.api.entity.DbRole;
import com.umpay.rms.gpd.user.api.entity.DbUserRole;
import com.umpay.rms.gpd.user.mapper.DbUserRoleMapper;
import com.umpay.rms.gpd.user.service.DbUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色表(DbUserRole)表服务实现类
 *
 * @author makejava
 * @since 2020-06-02 14:45:40
 */
@Service("UserRoleService")
public class DbUserRoleServiceImpl implements DbUserRoleService {
    Logger logger = LoggerFactory.getLogger( this.getClass() );
    @Resource
    private DbUserRoleMapper dbUserRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbUserRole queryById(Long id) {
        return this.dbUserRoleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbUserRole> queryAllByLimit(int offset, int limit) {
        return this.dbUserRoleDao.queryAllByLimit(offset, limit);
    }
  /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbUserRole> queryAll(DbUserRole dbUserRole) {
        return this.dbUserRoleDao.queryAll(dbUserRole);
    }
    /**
     * 新增数据
     *
     * @param dbUserRole 实例对象
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbUserRole insert(DbUserRole dbUserRole) {
        this.dbUserRoleDao.insert(dbUserRole);
        return dbUserRole;
    }

    /**
     * 修改数据
     *
     * @param dbUserRole 实例对象
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbUserRole update(DbUserRole dbUserRole) {
        this.dbUserRoleDao.update(dbUserRole);
        return this.queryById(Long.parseLong(""+dbUserRole.getId()));
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public boolean deleteById(Long id) {
        return this.dbUserRoleDao.deleteById(id) > 0;
    }

    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbRole> selectUserRole(DbUserRole dbUserRole) {
         return this.dbUserRoleDao.selectUserRole(dbUserRole);
    }

    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int updateUserRole(List<DbUserRole> userRoleList) {
        return this.dbUserRoleDao.updateUserRole(userRoleList);
    }
}