package com.umpay.rms.gpd.user.service.impl;




import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.user.api.entity.DbPermission;
import com.umpay.rms.gpd.user.api.entity.DbRolePermission;
import com.umpay.rms.gpd.user.mapper.DbRolePermissionMapper;
import com.umpay.rms.gpd.user.service.DbRolePermissionService;
import com.umpay.rms.gpd.user.util.CommonMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限表(DbRolePermission)表服务实现类
 *
 * @author makejava
 * @since 2020-06-02 15:10:18
 */
@Service("RolePermissionService")
public class DbRolePermissionServiceImpl implements DbRolePermissionService {
    Logger logger = LoggerFactory.getLogger( this.getClass() );
    @Resource
    private DbRolePermissionMapper dbRolePermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbRolePermission queryById(Long id) {
        return this.dbRolePermissionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbRolePermission> queryAllByLimit(int offset, int limit) {
        return this.dbRolePermissionDao.queryAllByLimit(offset, limit);
    }
    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbRolePermission> queryAll(DbRolePermission dbRolePermission) {
        return this.dbRolePermissionDao.queryAll(dbRolePermission);
    }
    /**
     * 新增数据
     *
     * @param dbRolePermission 实例对象
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbRolePermission insert(DbRolePermission dbRolePermission) {
        this.dbRolePermissionDao.insert(dbRolePermission);
        return dbRolePermission;
    }

    /**
     * 修改数据
     *
     * @param dbRolePermission 实例对象
     * @return 实例对象
     */
    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg update(DbRolePermission dbRolePermission) {
        int result = this.dbRolePermissionDao.update(dbRolePermission);
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
        return this.dbRolePermissionDao.deleteById(id) > 0;
    }

    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbPermission> selectRolePermission(Integer userId) {
        return this.dbRolePermissionDao.selectRolePermission(userId);

    }
}