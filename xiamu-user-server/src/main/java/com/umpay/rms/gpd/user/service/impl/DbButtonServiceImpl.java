package com.umpay.rms.gpd.user.service.impl;



import com.umpay.rms.gpd.user.api.entity.DbButton;
import com.umpay.rms.gpd.user.mapper.DbButtonMapper;
import com.umpay.rms.gpd.user.service.DbButtonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class DbButtonServiceImpl implements DbButtonService {

    @Resource(type = DbButtonMapper.class)
    private DbButtonMapper dbButtonDao;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbButtonMapper getDbButtonDao() {
        return dbButtonDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbButton getById(Integer id) {
        return dbButtonDao.getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbButton getByEntity(DbButton dbButton) {
        return dbButtonDao.getByEntity(dbButton);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbButton> listByEntity(DbButton dbButton) {
        return dbButtonDao.listByEntity(dbButton);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbButton> listByIds(List<DbButton> ids) {
        return dbButtonDao.listByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int insert(DbButton dbButton) {
        Date date = new Date();
        dbButton.setCreateTime(date);
        return dbButtonDao.insert(dbButton);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int insertBatch(List<DbButton> list) {
        return dbButtonDao.insertBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int update(DbButton dbButton) {
        return dbButtonDao.update(dbButton);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int updateBatch(List<DbButton> list) {
        return dbButtonDao.updateBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int deleteById(Integer id) {
        return dbButtonDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int deleteByEntity(DbButton dbButton) {
        return dbButtonDao.deleteByEntity(dbButton);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int deleteByIds(List<DbButton> list) {
        return dbButtonDao.deleteByIds(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int countAll() {
        return dbButtonDao.countAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int countByEntity(DbButton dbButton) {
        return dbButtonDao.countByEntity(dbButton);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Set<String> selectButtonByUserId(Integer id) {
        return dbButtonDao.selectButtonByUserId(id);
    }

}