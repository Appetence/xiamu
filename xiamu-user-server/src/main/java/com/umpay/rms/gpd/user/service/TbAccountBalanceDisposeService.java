package com.umpay.rms.gpd.user.service;



import com.umpay.rms.gpd.user.api.entity.TbAccountBalance;

import java.util.List;
import java.util.Vector;

/**
 * @program: rms-gpd
 * @description: 账户校准
 * @author: xiamu
 * @create: 2020-06-17 18:04
 */
public interface TbAccountBalanceDisposeService {

    //查询所有账户
    public List<TbAccountBalance> quallAccount();
    //批量修改账户
    public int batchUpdateAccount(Vector<TbAccountBalance> tbAccounts);

}
