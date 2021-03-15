package com.umpay.rms.gpd.user.service.impl;


import com.umpay.rms.gpd.user.api.entity.TbAccountBalance;
import com.umpay.rms.gpd.user.mapper.TbAccountBalanceMapper;
import com.umpay.rms.gpd.user.service.TbAccountBalanceDisposeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Vector;

/**
 * @program: rms-gpd
 * @description:
 * @author: xiamu
 * @create: 2020-07-17 17:03
 */
@Service
public class TbAccountBalanceDisposeServiceImpl implements TbAccountBalanceDisposeService {
    @Resource
    TbAccountBalanceMapper tbAccountBalanceDao;
    //查询所有账户
    public List<TbAccountBalance> quallAccount(){
        return tbAccountBalanceDao.queryAll();
    }
    //批量修改账户
    public int batchUpdateAccount(Vector<TbAccountBalance> tbAccounts){
        return tbAccountBalanceDao.batchUpdateAccount(tbAccounts);
    }
}
