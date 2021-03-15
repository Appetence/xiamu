package com.umpay.rms.gpd.user.mapper;



import com.umpay.rms.gpd.user.api.entity.TbAccount;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * (TbAccount)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-14 09:44:55
 */
public interface TbAccountMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param accountId 主键
     * @return 实例对象
     */
    TbAccount queryById(String accountId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbAccount> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询指所有
     *
     * @return 对象列表
     */
    List<TbAccount> queryAll();
    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbAccount 实例对象
     * @return 对象列表
     */
    List<TbAccount> queryAll(TbAccount tbAccount);

    /**
     * 新增数据
     *
     * @param tbAccount 实例对象
     * @return 影响行数
     */
    int insert(TbAccount tbAccount);

    /**
     * 修改数据
     *
     * @param tbAccount 实例对象
     * @return 影响行数
     */
    int update(TbAccount tbAccount);

    /**
     * 通过主键删除数据
     *
     * @param accountId 主键
     * @return 影响行数
     */
    int deleteById(Long accountId);

    List<TbAccount> selectAccountMessage(TbAccount tbAccount);

    List<TbAccount> selectAccountCheck(TbAccount tbAccount);

    List<TbAccount> selectSunAccount(TbAccount dbUser);

    Integer countSunAccountSend(TbAccount tbAccount);

    Integer countAccountSendNumber(TbAccount tbAccount);

    Integer batchUpdateAccountBy(List<TbAccount> tbAccounts);

    Integer countSendNumberByAccount(TbAccount tbAccountBalance);

    Integer countAccountSendNumberByAccount(TbAccount tbAccount);

    TbAccount countAccountTodayReceive(TbAccount tbAccount);

    List<TbAccount> selectDelegates(TbAccount tbAccount);

    List<TbAccount> selectAccountMessages(TbAccount dbUser);

    List<TbAccount> selectAccountChecks(TbAccount dbUser);

    Integer countSunAccountSends(@Param(value = "accountId") String accountId);

    List<TbAccount> getAccountInfoByAccountIds(ArrayList<String> list);
}