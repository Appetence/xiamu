package com.umpay.rms.gpd.user.service;


import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.user.api.entity.TbAccount;
import com.umpay.rms.gpd.user.api.entity.TbAccountBalance;
import com.umpay.rms.gpd.user.api.entity.TbAccountRecord;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * (TbAccount)表服务接口
 *
 * @author makejava
 * @since 2020-06-14 09:44:55
 */
@Component
public interface TbAccountService {

    /**
     * 通过ID查询单条数据
     *
     * @param accountId 主键
     * @return 实例对象
     */
    TbAccount queryById(String accountId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbAccount> queryAllByLimit(int offset, int limit);
    /**
     * 查询全部数据
     *
     * @return 对象列表
     */
    List<TbAccount> queryAll(TbAccount tbAccount);


    /**
     * 新增数据
     *
     * @param tbAccount 实例对象
     * @return 实例对象
     */
    TbAccount insert(TbAccount tbAccount) throws SQLException;

    /**
     * 修改数据
     *
     * @param tbAccount 实例对象
     * @return 实例对象
     */
    Msg update(TbAccount tbAccount);

    /**
     * 通过主键删除数据
     *
     * @param accountId 主键
     * @return 是否成功
     */
    boolean deleteById(Long accountId);

    List<TbAccount> selectAccountMessage(TbAccount dbUser);

    List<TbAccount> selectAccountCheck(TbAccount dbUser);

    List<TbAccount> selectSunAccount(TbAccount dbUser);

    int countSunAccountSend(TbAccount tbAccount);

    int countAccountSendNumber(TbAccount tbAccount);

    int countAccountSendNumberByAccount(TbAccount tbAccount);

    int countSendNumberByAccount(TbAccount tbAccountBalance);

    TbAccount countAccountTodayReceive(TbAccount tbAccount);

    List<TbAccount> selectDelegates(TbAccount tbAccount);

    int updateUserByAccount(String accountId, Integer id, int i);

    List<TbAccount> selectAccountMessages(TbAccount dbUser);

    List<TbAccount> selectAccountChecks(TbAccount dbUser);

    Msg updateAccountUserStatus(String accountId, int userId, Integer roleId);

    Msg updateAccountBalance(TbAccountRecord tbAccountRecord, String shareAccount, String userId);

    Msg selectSunAccountInfo(TbAccountBalance tbAccountBalance);

    Msg countDelegateAccountSumBalanceAndWeekRechange(String accountId);

    Msg countDelegateAccountBalance(String accountId);

    Msg countAccountTodayInfoByUserName(String username);

    Msg creataAccountId();

    Msg updateAccountCodeNumber(String accountId, int[] codetype, String[] codeNumber);

    Msg updateAccountDelegate(String accountId, String delegate);

    Msg insertUserInfo(String company, String username, String remark, Integer status, String parentAccountId);

    Msg checkCompanyInfo(String accountId, Integer authenticationStatus, String remark, int[] codetype, String[] codeNumber, String username);

    Msg selectSunAccountByAccountId(String companyName, String authenticationStatus, Integer pageNum, Integer pageSize, String accountId);

    Msg updatetUserInfo(TbAccount tbAccount);

    Msg updateAccountAgreementByAccountId(TbAccount tbAccount);

    List<TbAccount> getAccountInfoByAccountIds(String[] accountIds);
}