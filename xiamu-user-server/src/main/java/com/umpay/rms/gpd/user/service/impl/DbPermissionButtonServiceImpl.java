package com.umpay.rms.gpd.user.service.impl;


import com.umpay.rms.gpd.user.api.entity.DbPermissionButton;
import com.umpay.rms.gpd.user.mapper.DbPermissionButtonMapper;
import com.umpay.rms.gpd.user.service.DbPermissionButtonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DbPermissionButtonServiceImpl implements DbPermissionButtonService {

    @Resource(type = DbPermissionButtonMapper.class)
    private DbPermissionButtonMapper dbPermissionButtonDao;

    @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbPermissionButtonMapper getDbPermissionButtonDao() {
        return dbPermissionButtonDao;
    }
   @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbPermissionButton getById(Integer id) {
        return dbPermissionButtonDao.getById(id);
    }
   @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbPermissionButton getByEntity(DbPermissionButton dbPermissionButton) {
        return dbPermissionButtonDao.getByEntity(dbPermissionButton);
    }
   @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbPermissionButton> listByEntity(DbPermissionButton dbPermissionButton) {
        return dbPermissionButtonDao.listByEntity(dbPermissionButton);
    }
   @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbPermissionButton> listByIds(List<Integer> ids) {
        return dbPermissionButtonDao.listByIds(ids);
    }
   @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int insert(DbPermissionButton dbPermissionButton) {

        return dbPermissionButtonDao.insert(dbPermissionButton);
    }
   @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int insertBatch(List<DbPermissionButton> list) {
        return dbPermissionButtonDao.insertBatch(list);
    }
   @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int update(DbPermissionButton dbPermissionButton) {
        return dbPermissionButtonDao.update(dbPermissionButton);
    }
   @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int updateBatch(List<DbPermissionButton> list) {
        return dbPermissionButtonDao.updateBatch(list);
    }
   @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int deleteById(Integer id) {
        return dbPermissionButtonDao.deleteById(id);
    }
   @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int deleteByEntity(DbPermissionButton dbPermissionButton) {
        return dbPermissionButtonDao.deleteByEntity(dbPermissionButton);
    }
     @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int deleteByIds(List<Integer> list) {
        return dbPermissionButtonDao.deleteByIds(list);
    }
   @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int countAll() {
        return dbPermissionButtonDao.countAll();
    }
       @Override  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int countByEntity(DbPermissionButton dbPermissionButton) {
        return dbPermissionButtonDao.countByEntity(dbPermissionButton);
    }

}