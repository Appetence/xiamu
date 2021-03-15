package com.umpay.rms.gpd.user.service.impl;



import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.internal.dto.ResponseResult;
import com.umpay.rms.gpd.user.api.entity.*;
import com.umpay.rms.gpd.user.api.request.UserRegisterRequest;
import com.umpay.rms.gpd.user.mapper.DbPermissionMapper;
import com.umpay.rms.gpd.user.mapper.DbUserMapper;
import com.umpay.rms.gpd.user.service.*;
import com.umpay.rms.gpd.user.util.CommonMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * (DbUser)表服务实现类
 *
 * @author makejava
 * @since 2020-06-02 14:51:17
 */

@Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
@Service(value = "dbUserService")
public class DbUserServiceImpl implements DbUserService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private DbUserMapper dbUserDao;
    @Resource
    private DbUserService dbUserService;

    @Resource
    private DbRoleService dbRoleService;
    @Resource
    private DbPermissionService dbPermissionService;
    @Resource
    private DbRolePermissionService dbRolePermissionService;
    @Resource
    private DbUserRoleService dbUserRoleService;
    @Resource
    private DbPermissionMapper dbPermissionDao;

    @Resource
    private TbAccountService tbAccountService;
    @Resource
    private TbAccountCodeNumberService tbAccountCodeNumberService;
    @Resource
    private DbExaminationApprovalService dbExaminationApprovalService;
    @Resource
    private TbAccountBalanceService tbAccountBalanceService;


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbUser queryById(Integer id) {
        try {
            DbUser dbUser = this.dbUserDao.queryById(id);
            return dbUser;
        } catch (Exception e) {
            logger.error("user详情查询异常{}", e);
        }
        return null;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbUser> queryAllByLimit(int offset, int limit) {
        return this.dbUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbUser> queryAll(DbUser dbUser) {
        return this.dbUserDao.queryAll(dbUser);
    }

    /**
     * 新增数据
     *
     * @param dbUser 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbUser insert(DbUser dbUser) {
        this.dbUserDao.insert(dbUser);
        return dbUser;
    }

    /**
     * 修改数据
     *
     * @param dbUser 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg update(DbUser dbUser) {
        int result = this.dbUserDao.update(dbUser);
        if (result > 0) {
            return Msg.returnSuccessMsg(CommonMessageUtil.OPERATION_SUCCESS, dbUser);
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
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public boolean deleteById(Integer id) {
        return this.dbUserDao.deleteById(id) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg selectUserInfoByUserName(String username) {
        DbUser dbUser = new DbUser();
        dbUser.setUsername(username);
        List<DbUser> list = dbUserDao.selectUserByUser(dbUser);
        String accountId = list.get(0).getAccountId();
        TbAccount tbAccount = tbAccountService.queryById(accountId);
        if (tbAccount != null) {
            TbAccountCodeNumber tbAccountCodeNumber = new TbAccountCodeNumber();
            tbAccountCodeNumber.setAccountId(accountId);
            List<TbAccountCodeNumber> tbAccountCodeNumberList = tbAccountCodeNumberService.queryAll(tbAccountCodeNumber);
            if (CollectionUtils.isEmpty(tbAccountCodeNumberList)) {
                logger.info("暂无码号信息");
            } else {
                tbAccount.setTbAccountCodeNumber(tbAccountCodeNumberList);
            }
            //审批详情
            DbExaminationApproval dbExaminationApproval = new DbExaminationApproval();
            dbExaminationApproval.setApplicantId(accountId);
            List<DbExaminationApproval> listdDbExaminationApprovals = dbExaminationApprovalService.queryAll(dbExaminationApproval);
            if (CollectionUtils.isEmpty(listdDbExaminationApprovals)) {
                logger.info("暂时无管理员审批信息");
            } else {
                tbAccount.setDbExaminationApproval(listdDbExaminationApprovals.get(0));
            }
            list.get(0).setTbAccount(tbAccount);

            //检查角色
            DbUserRole dbUserRole = new DbUserRole();
            dbUserRole.setUserId(list.get(0).getId());
            List<DbRole> roleList = dbUserRoleService.selectUserRole(dbUserRole);
            if (CollectionUtils.isEmpty(roleList)) {
                logger.info("用户角色为空");
            } else {
                list.get(0).setRoles(roleList);
            }
            //查权限
            List<DbPermission> listPermission = dbPermissionDao.findPermissionByUserId(String.valueOf(list.get(0).getId()));
            if (CollectionUtils.isEmpty(listPermission)) {
                logger.info("用户权限为空");
            } else {
                list.get(0).setPermissions(listPermission);
            }
            return Msg.returnSuccessMsg(CommonMessageUtil.USER_SUCCESS, list);

        } else {
            return Msg.returnErrorMsg("未查询到该用户码号信息");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbRole> selectUserRolePermission(Integer userId) {

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Boolean checkUserName(String dbUsername) {
        DbUser dbUser = new DbUser();
        dbUser.setUsername(dbUsername);
        List<DbUser> dbUsers = dbUserDao.queryAll(dbUser);
        if (CollectionUtils.isEmpty(dbUsers)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Boolean checkCompanyName(String companyname) {
        DbUser dbUser = new DbUser();
        dbUser.setName(companyname);
        List<DbUser> dbUsers = dbUserDao.queryAll(dbUser);
        if (CollectionUtils.isEmpty(dbUsers)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbUser> selectUserManager(DbUser dbUser) {
        return dbUserDao.selectUserManager(dbUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Boolean checkAccountId(String accountId) {
        DbUser dbUser = new DbUser();
        dbUser.setAccountId(accountId);
        List<DbUser> list = dbUserDao.queryAll(dbUser);
        if (CollectionUtils.isEmpty(list)) {
            return true;//不重复，可用
        } else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public DbUser quareUserInfoByUsername(String username) {
        DbUser dbUser = new DbUser();
        dbUser.setUsername(username);
        List<DbUser> list = dbUserDao.queryAll(dbUser);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbUser> selectUserInfo(DbUser dbUser) {
        List<DbUser> list = dbUserDao.selectUserInfo(dbUser);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg selectAccountInfo(String accId) {


        String accountId = accId;// Long.parseLong(accId);
        TbAccount tbAccount = tbAccountService.queryById(accountId);
        if (tbAccount != null) {
            TbAccountBalance tbAccountBalance = new TbAccountBalance();
            tbAccountBalance.setAcctId(accountId);
            //查余额
            List<TbAccountBalance> tbAccountBalanceList = tbAccountBalanceService.queryAll(tbAccountBalance);
            tbAccount.setBalanceNumber(tbAccountBalanceList.get(0).getBalance());
            TbAccountCodeNumber tbAccountCodeNumber = new TbAccountCodeNumber();
            tbAccountCodeNumber.setAccountId(accountId);
            List<TbAccountCodeNumber> list = tbAccountCodeNumberService.queryAll(tbAccountCodeNumber);
            if (CollectionUtils.isEmpty(list)) {
                logger.info("暂无码号信息");
            } else {
                Map<String, String> map = new HashMap<>();
                for (TbAccountCodeNumber tbAccountCodeNumber1 : list) {
                    String codeType = "";
                    if (tbAccountCodeNumber1.getCodeType() == 1) {
                        codeType = "mobile";

                    }
                    if (tbAccountCodeNumber1.getCodeType() == 2) {
                        codeType = "unition";
                    }
                    if (tbAccountCodeNumber1.getCodeType() == 3) {
                        codeType = "telecom";
                    }
                    map.put(codeType, tbAccountCodeNumber1.getCodeNumber());
                }
                tbAccount.setCodeNumbers(map);
            }
            //审批详情
            DbExaminationApproval dbExaminationApproval = new DbExaminationApproval();
            dbExaminationApproval.setApplicantId(accountId);
            List<DbExaminationApproval> listdDbExaminationApprovals = dbExaminationApprovalService.queryAll(dbExaminationApproval);
            if (CollectionUtils.isEmpty(listdDbExaminationApprovals)) {
                logger.info("暂时无管理员审批信息");
            } else {
                tbAccount.setDbExaminationApproval(listdDbExaminationApprovals.get(0));
            }
            DbUser dbUser = new DbUser();
            dbUser.setAccountId(accountId);
            List<DbUser> userList = dbUserDao.queryAll(dbUser);
            tbAccount.setDbUsers(userList);
            //根据用户查询角色
            DbUserRole dbUserRole = new DbUserRole();
            dbUserRole.setUserId(userList.get(0).getId());
            List<DbUserRole> dbUserRoleList = dbUserRoleService.queryAll(dbUserRole);
            List<DbRole> dbRoles = new LinkedList<>();
            for (DbUserRole dbUserRole1 : dbUserRoleList) {
                DbRole dbrole1 = dbRoleService.queryById((long) dbUserRole1.getRoleId());
                dbRoles.add(dbrole1);
            }
            if (CollectionUtils.isEmpty(dbRoles)) {
                logger.info("该用户权限为空");
            } else {
                tbAccount.setDbRole(dbRoles.get(0));

            }

        } else {
            return Msg.returnErrorMsg("未查询到该用户码号信息");
        }       //code

   /*         //检查角色
            DbUserRole dbUserRole = new DbUserRole();
            dbUserRole.setUserId(userId);
            List<DbRole> roleList = dbUserRoleService.selectUserRole(dbUserRole);
            if (CollectionUtils.isEmpty(roleList)) {
                logger.info("用户角色为空");
            } else {
                dbUser.setRoles(roleList);
            }
            //查权限
            List<DbPermission> listPermission = dbPermissionDao.findPermissionByUserId(userId+"");
            if (CollectionUtils.isEmpty(listPermission)) {
                logger.info("用户权限为空");
            } else {
                dbUser.setPermissions(listPermission);
            }*/
        return Msg.returnSuccessMsg(CommonMessageUtil.USER_SUCCESS, tbAccount);


    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public boolean checkUserRole(String username) {
        DbUser dbUser = new DbUser();
        dbUser.setUsername(username);
        List<DbRole> list = dbUserDao.checkUserRole(dbUser);
        for (DbRole dbRole : list) {
            if (dbRole.getId() == 37) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public boolean checkUserRoleByAccountId(String accountId) {
        TbAccount tbAccount = new TbAccount();
        tbAccount.setAccountId(accountId);

        List<DbRole> list = dbUserDao.checkUserRoleByAccountId(tbAccount);
        for (DbRole dbRole : list) {
            if (dbRole.getId() == 37) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<DbUser> selectUserKey(DbUser dbUser) {
        return dbUserDao.selectUserKey(dbUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public ResponseResult<String> onlineRegister(UserRegisterRequest userInfoRequest) {
        Msg msg = tbAccountService.insertUserInfo(null, userInfoRequest.getMobile(), userInfoRequest.getPassword(), 1, null);
        if (msg.isSuccess()) {
            return ResponseResult.success(msg.getMessage());
        } else {
            return ResponseResult.fail(msg.getMessage());
        }

    }

    @Override
    public Msg getAccountInfoByAccountIds(String[] accountIds) {
        List<TbAccount> list = tbAccountService.getAccountInfoByAccountIds(accountIds);
        return Msg.returnSuccessMsg("", list);
    }
}