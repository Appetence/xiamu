package com.umpay.rms.gpd.user.service.impl;



import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.user.api.entity.DbRole;
import com.umpay.rms.gpd.user.util.CommonMessageUtil;
import com.umpay.rms.gpd.user.mapper.DbRoleMapper;
import com.umpay.rms.gpd.user.service.DbRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色表(DbRole)表服务实现类
 *
 * @author makejava
 * @since 2020-06-02 14:51:10
 */
@Service("RoleService")
public class DbRoleServiceImpl implements DbRoleService {
    Logger logger = LoggerFactory.getLogger( this.getClass() );
    @Resource
    private DbRoleMapper dbRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbRole queryById(Long id) {
        return this.dbRoleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbRole> queryAllByLimit(int offset, int limit) {
        return this.dbRoleDao.queryAllByLimit(offset, limit);
    }
  /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbRole> queryAll(DbRole dbRole) {
        return this.dbRoleDao.queryAll(dbRole);
    }
    /**
     * 新增数据
     *
     * @param dbRole 实例对象
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbRole insert(DbRole dbRole) {
        this.dbRoleDao.insert(dbRole);
        return dbRole;
    }

    /**
     * 修改数据
     *
     * @param dbRole 实例对象
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg update(DbRole dbRole) {
        int result = this.dbRoleDao.update(dbRole);
        if (result > 0) {
            return Msg.returnSuccessMsg(CommonMessageUtil.OPERATION_SUCCESS);
        } else {
            return Msg.returnSuccessMsg(CommonMessageUtil.OPERATION_FAIL);
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public boolean deleteById(Long id) {
        return this.dbRoleDao.deleteById(id) > 0;
    }
}