package com.umpay.rms.gpd.user.service;




import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.internal.dto.ResponseResult;
import com.umpay.rms.gpd.user.api.entity.DbRole;
import com.umpay.rms.gpd.user.api.entity.DbUser;
import com.umpay.rms.gpd.user.api.request.UserRegisterRequest;

import java.util.List;

/**
 * (DbUser)表服务接口
 *
 * @author makejava
 * @since 2020-06-02 14:51:17
 */
public interface DbUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DbUser queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbUser> queryAllByLimit(int offset, int limit);
    /**
     * 查询全部数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DbUser> queryAll(DbUser dbUser);
    /**
     * 新增数据
     *
     * @param dbUser 实例对象
     * @return 实例对象
     */
    DbUser insert(DbUser dbUser);

    /**
     * 修改数据
     *
     * @param dbUser 实例对象
     * @return 实例对象
     */
    Msg update(DbUser dbUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Msg selectUserInfoByUserName(String username);

    List<DbRole> selectUserRolePermission(Integer userId);


    Boolean checkUserName(String username);

    Boolean checkCompanyName(String companyname);

    List<DbUser> selectUserManager(DbUser dbUser);

    Boolean checkAccountId(String accountId);

    DbUser quareUserInfoByUsername(String username);

    List<DbUser> selectUserInfo(DbUser dbUser);

    Msg selectAccountInfo(String accountId);

    boolean checkUserRole(String username);

    boolean checkUserRoleByAccountId(String username);

    List<DbUser> selectUserKey(DbUser dbUser);

    ResponseResult<String> onlineRegister(UserRegisterRequest userInfoRequest);

    Msg getAccountInfoByAccountIds(String[] accountIds);
}