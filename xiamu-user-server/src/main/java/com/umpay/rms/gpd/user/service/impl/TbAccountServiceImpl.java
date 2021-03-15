package com.umpay.rms.gpd.user.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


import com.umpay.rms.gpd.internal.constatnt.BalanceType;
import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.user.api.entity.*;
import com.umpay.rms.gpd.user.constant.BalanceConstants;
import com.umpay.rms.gpd.user.constant.RoleConstants;
import com.umpay.rms.gpd.user.util.CommonMessageUtil;
import com.umpay.rms.gpd.user.util.RandomUtil;
import com.umpay.rms.gpd.user.util.RedisLock;
import com.umpay.rms.gpd.user.mapper.TbAccountMapper;
import com.umpay.rms.gpd.user.mapper.TbChannelInfoMapper;
import com.umpay.rms.gpd.user.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;

/**
 * (TbAccount)表服务实现类
 *
 * @author makejava
 * @since 2020-06-14 09:44:55
 */
@Service("tbAccountService")
public class TbAccountServiceImpl implements TbAccountService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TbAccountMapper tbAccountMapper;
    @Resource
    private DbUserService dbUserService;
    @Resource
    private AccountSMSBalanceService accountSMSBalanceService;
    @Resource
    private DbUserRoleService dbUserRoleService;
    @Resource
    private DbRoleService dbRoleService;
    @Autowired(required = false)
    private PrepayService prepayService;
    @Resource
    private TbAccountBalanceService accountBalanceService;
    @Resource
    private TbAccountRecordService tbAccountRecordService;
    @Resource
    private RedisLock redisLock;
    @Resource
    private TbAccountService tbAccountService;
    @Resource
    private TbChannelInfoMapper tbChannelInfoMapper;

    @Resource
    private DbExaminationApprovalService dbExaminationApprovalService;
    @Resource
    private TbAccountCodeNumberService tbAccountCodeNumberService;

    /**
     * 通过ID查询单条数据
     *
     * @param accountId 主键
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public TbAccount queryById(String accountId) {
        return this.tbAccountMapper.queryById(accountId);
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
    public List<TbAccount> queryAllByLimit(int offset, int limit) {
        return this.tbAccountMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<TbAccount> queryAll(TbAccount tbAccount) {
        return this.tbAccountMapper.queryAll(tbAccount);
    }

    /**
     * 新增数据
     *
     * @param tbAccount 实例对象
     * @return 实例对象
     */
    @Override
    public TbAccount insert(TbAccount tbAccount) throws SQLException {
        this.tbAccountMapper.insert(tbAccount);
        return tbAccount;
    }

    /**
     * 修改数据
     *
     * @param tbAccount 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg update(TbAccount tbAccount) {
        int result = this.tbAccountMapper.update(tbAccount);
        if (result > 0) {
            return Msg.returnSuccessMsg("提交成功");
        } else {
            return Msg.returnErrorMsg("修改失败");
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param accountId 主键
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public boolean deleteById(Long accountId) {
        return this.tbAccountMapper.deleteById(accountId) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<TbAccount> selectAccountMessage(TbAccount tbAccount) {
        return this.tbAccountMapper.selectAccountMessage(tbAccount);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<TbAccount> selectAccountCheck(TbAccount tbAccount) {
        return this.tbAccountMapper.selectAccountCheck(tbAccount);

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<TbAccount> selectSunAccount(TbAccount dbUser) {
        return this.tbAccountMapper.selectSunAccount(dbUser);
    }

    @Override
    public int countSunAccountSend(TbAccount tbAccount) {

        Integer result = this.tbAccountMapper.countSunAccountSends(tbAccount.getAccountId());
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int countAccountSendNumber(TbAccount tbAccount) {
        Integer result = this.tbAccountMapper.countAccountSendNumber(tbAccount);
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int countAccountSendNumberByAccount(TbAccount tbAccount) {
        Integer result = this.tbAccountMapper.countAccountSendNumberByAccount(tbAccount);
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int countSendNumberByAccount(TbAccount tbAccountBalance) {
        Integer result = this.tbAccountMapper.countSendNumberByAccount(tbAccountBalance);
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public TbAccount countAccountTodayReceive(TbAccount tbAccount) {
        return this.tbAccountMapper.countAccountTodayReceive(tbAccount);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<TbAccount> selectDelegates(TbAccount tbAccount) {
        return this.tbAccountMapper.selectDelegates(tbAccount);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int updateUserByAccount(String accountId, Integer id, int roleId) {

        //查询该账户下用户数量
        DbUser dbUser = new DbUser();
        dbUser.setAccountId(accountId);
        List<DbUser> dbUserList = dbUserService.queryAll(dbUser);
        if (CollectionUtils.isEmpty(dbUserList)) {
            logger.info("该账户没有绑定用户");
            return 0;
        } else {
            List<DbUserRole> userRoleList = new LinkedList<>();
            for (DbUser user : dbUserList) {
                DbUserRole dbUserRole = new DbUserRole();
                dbUserRole.setRoleId(roleId);
                dbUserRole.setUserId(user.getId());
                userRoleList.add(dbUserRole);
            }
            int result = dbUserRoleService.updateUserRole(userRoleList);
            return result;
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<TbAccount> selectAccountMessages(TbAccount dbUser) {
        return this.tbAccountMapper.selectAccountMessages(dbUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public List<TbAccount> selectAccountChecks(TbAccount dbUser) {
        return this.tbAccountMapper.selectAccountChecks(dbUser);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Msg updateAccountUserStatus(String accountId, int userId, Integer roleId) {
        //校验角色是否有效
        DbRole dbRole = new DbRole();
        dbRole.setId(roleId);
        List<DbRole> dbRoles = dbRoleService.queryAll(dbRole);
        if (CollectionUtils.isEmpty(dbRoles)) {
            return Msg.returnErrorMsg("查无此角色");
        } else {
            //查询该账户下用户数量
            DbUser dbUser = new DbUser();
            dbUser.setAccountId(accountId);
            List<DbUser> dbUserList = dbUserService.queryAll(dbUser);
            if (CollectionUtils.isEmpty(dbUserList)) {
                return Msg.returnErrorMsg("该账户没有绑定用户");
            } else {

                List<DbUserRole> userRoleList = new LinkedList<>();
                for (DbUser user : dbUserList) {
                    DbUserRole dbUserRole = new DbUserRole();
                    dbUserRole.setRoleId(roleId);
                    dbUserRole.setUserId(user.getId());
                    userRoleList.add(dbUserRole);
                }
                try {
                    int result = dbUserRoleService.updateUserRole(userRoleList);
                    if (result > 0) {
                        return Msg.returnSuccessMsg("更新数据成功");
                    } else {
                        return Msg.returnErrorMsg("更新数据0条");
                    }
                } catch (Exception e) {
                    logger.error("角色更改异常：{}", e);
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
                return Msg.returnErrorMsg("更新数据失败");
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg updateAccountBalance(TbAccountRecord tbAccountRecord, String shareAccount, String userId) {
        String regex = "^[1-9]+[0-9]*$";

        if (tbAccountRecord == null /*|| !Pattern.matches(regex, shareAccount)*/ || tbAccountRecord.getNumber() == null || tbAccountRecord.getNumber() < 1 || !RandomUtil.isNumeric(tbAccountRecord.getNumber().toString())) {
            return Msg.returnSuccessMsg("请输入合法数字");
        }

        tbAccountRecord.setOperatUserId(Integer.parseInt(userId));
        tbAccountRecord.setOperatAccId(shareAccount);
        TbAccountBalance tbAccountBalance = new TbAccountBalance();
        tbAccountBalance.setAcctId(tbAccountRecord.getAccId());
        List<TbAccountBalance> tbAccountBalanceList = accountBalanceService.queryAll(tbAccountBalance);

        if (CollectionUtils.isEmpty(tbAccountBalanceList) || tbAccountRecord.getNumber() <= 0) {
            return Msg.returnErrorMsg("录入信息有误，请核对", tbAccountRecord.getId());
        } else {
            tbAccountBalance = tbAccountBalanceList.get(0);
            tbAccountRecord.setBeforNumber(tbAccountBalanceList.get(0).getBalance());
            tbAccountRecord.setCreateTime(new Date());
            //子账户充值
            if (tbAccountRecord.getFlag() == 1) {
                //管理员不扣自己的条数
                //判断操作用户是否为超级管理员
                try {
                    boolean roleBoolean = dbUserService.checkUserRoleByAccountId(tbAccountRecord.getOperatAccId().toString());
                    if (!roleBoolean) {
                        return Msg.returnErrorMsg("用户身份不合法");
                    }
                } catch (Exception e) {
                    logger.error("用户身份查询异常{}", e);
                    return Msg.returnErrorMsg("用户身份查询异常");
                }

            }
/*            if (tbAccountRecord.getFlag() == 2) {
                tbAccountBalance.setBalance(tbAccountBalance.getBalance() - tbAccountRecord.getNumber());
            }*/
            if (tbAccountRecord.getFlag() == 3) {
                //代理商充值
                TbAccountBalance tbAccountBalanceManage = new TbAccountBalance();
                tbAccountBalanceManage.setAcctId(tbAccountRecord.getOperatAccId());
                List<TbAccountBalance> tbAccountBalanceManageList = accountBalanceService.queryAll(tbAccountBalanceManage);
                if (CollectionUtils.isEmpty(tbAccountBalanceManageList)) {
                    return Msg.returnErrorMsg("代理商信息未查到，处理失败");
                } else {
                    //代理商账户
                    tbAccountBalanceManage = tbAccountBalanceManageList.get(0);
                    Long managerAccountBalance = prepayService.billing(String.valueOf(tbAccountRecord.getOperatAccId()), tbAccountRecord.getNumber());
                    //分发情况下，代理商需要先扣自己的账户
                    if (tbAccountBalanceManage.getBalance() >= tbAccountRecord.getNumber() && managerAccountBalance >= 0) {
                        TbAccountRecord tbAccountRecordManager = new TbAccountRecord();
                        tbAccountRecordManager.setOperatAccId(tbAccountRecord.getOperatAccId());
                        tbAccountRecordManager.setNumber(tbAccountRecord.getNumber());
                        tbAccountRecordManager.setOperatUserId(tbAccountRecord.getOperatUserId());
                        tbAccountRecordManager.setCreateTime(new Date());
                        tbAccountRecordManager.setStatus(1);
                        tbAccountRecordManager.setAcctId(tbAccountRecord.getOperatAccId());
                        tbAccountRecordManager.setFlag(2);
                        tbAccountRecordManager.setBeforNumber(tbAccountBalanceManage.getBalance());
                        tbAccountRecordManager.setRemark("为子账户" + tbAccountRecord.getAccId() + "充值：" + tbAccountRecord.getNumber());
                        tbAccountBalanceManage.setBalance(managerAccountBalance);

                        //消费记录
                        try {
                            TbAccountRecord result = tbAccountRecordService.insert(tbAccountRecordManager);
                            int res = accountBalanceService.update(tbAccountBalanceManage);
                            if (res > 0) {
                            } else {
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                prepayService.refund(String.valueOf(tbAccountRecord.getOperatAccId()), tbAccountRecord.getNumber());
                                return Msg.returnErrorMsg("代理商余额不足");
                            }
                        } catch (Exception e) {
                            logger.error("代理商扣费异常{}", e);
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            prepayService.refund(String.valueOf(tbAccountRecord.getOperatAccId()), tbAccountRecord.getNumber());
                            return Msg.returnErrorMsg("代理商扣费异常");
                        }

                    } else {
                        return Msg.returnErrorMsg("亲，账户余额不足");
                    }
                }


            }
            //消费记录
            Long accountBalance = prepayService.charge(String.valueOf(tbAccountBalance.getAcctId()), tbAccountRecord.getNumber());
            tbAccountBalance.setBalance(accountBalance);
            tbAccountBalance.setSumBalance(tbAccountRecord.getNumber());
            try {
                TbAccountRecord result = tbAccountRecordService.insert(tbAccountRecord);
                //修改账户
                int resu = accountBalanceService.update(tbAccountBalance);
                if (resu > 0) {
                    return Msg.returnSuccessMsg("处理成功");
                } else {
                    prepayService.refund(String.valueOf(tbAccountRecord.getOperatAccId()), tbAccountRecord.getNumber());
                    prepayService.refund(String.valueOf(tbAccountBalance.getAcctId()), -tbAccountRecord.getNumber());
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return Msg.returnErrorMsg("充值失败");
                }

            } catch (Exception e) {
                logger.error("账户充值异常：{}", e);
                prepayService.refund(String.valueOf(tbAccountRecord.getOperatAccId()), tbAccountRecord.getNumber());
                prepayService.refund(String.valueOf(tbAccountBalance.getAcctId()), -tbAccountRecord.getNumber());
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Msg.returnErrorMsg("业务逻辑处理异常");
            }

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg selectSunAccountInfo(TbAccountBalance tbAccountBalance) {
        //当前账户余额
        List<TbAccountBalance> list = accountBalanceService.queryAll(tbAccountBalance);
        //客户总余额
        Long sunBalance = accountBalanceService.countBalanceBySunAccount(tbAccountBalance);
        //客户本周发送量
        Long sumSend = accountBalanceService.countSendNumberBySunAccount(tbAccountBalance);

        Map<String, Object> map = new HashMap<>();
        map.put("balance", list.get(0).getBalance());
        map.put("sunBalance", sunBalance);
        map.put("weekSend", sumSend);
        return Msg.returnSuccessMsg("处理成功", map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg countDelegateAccountSumBalanceAndWeekRechange(String accountId) {
        TbAccountBalance tbAccountBalance = new TbAccountBalance();
        tbAccountBalance.setAcctId(accountId);
        //所有代理商余额
        Long sunBalance = accountBalanceService.countDelegateAccountSumBalance(tbAccountBalance);

        //本周充值
        Long weekRechange = accountBalanceService.countDelegateAccountWeekRechange(tbAccountBalance);
        Map<String, String> map = new HashMap<>();
        map.put("sunBalance", sunBalance + "");
        map.put("weekRechange", weekRechange + "");

        return Msg.returnSuccessMsg("查询成功", map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg countDelegateAccountBalance(String accountId) {
        TbAccountBalance tbAccountBalance = new TbAccountBalance();
        tbAccountBalance.setAcctId(accountId);
        //当前账户余额
        List<TbAccountBalance> list = accountBalanceService.queryAll(tbAccountBalance);
        //统计该代理商本周充值记录
        if (CollectionUtils.isEmpty(list)) {
            return Msg.returnErrorMsg("未查询到账户余额信息");
        } else {
            tbAccountBalance = list.get(0);
            //zhou chongzhi shuliang
            tbAccountBalance = tbAccountRecordService.countAccountWeekRechangeNumber(tbAccountBalance);
        }
        return Msg.returnSuccessMsg("查询成功", tbAccountBalance);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg countAccountTodayInfoByUserName(String username) {
        DbUser dbUser = new DbUser();
        dbUser.setUsername(username);
        String accountId = null;
        List<DbUser> dbUserList = dbUserService.queryAll(dbUser);
        if (CollectionUtils.isEmpty(dbUserList)) {
            return Msg.returnErrorMsg("用户名未查询到");
        } else {
            accountId = dbUserList.get(0).getAccountId();
        }
        TbAccount tbAccount1 = tbAccountService.queryById(accountId);
        TbAccount tbAccount = new TbAccount();
        tbAccount.setSecurityCertificate(null);
        tbAccount.setBusinessLicense(null);
        TbAccountBalance tbAccountBalance = new TbAccountBalance();
        tbAccountBalance.setAcctId(accountId);
        List<TbAccountBalance> balanceList = accountBalanceService.queryAll(tbAccountBalance);
        if (CollectionUtils.isEmpty(balanceList)) {
            return Msg.returnErrorMsg("账户不存在");
        } else {
            try {

                tbAccount.setAccountId(accountId);
                tbAccount = tbAccountService.countAccountTodayReceive(tbAccount);
                tbAccount.setCompany(tbAccount1.getCompany());
                tbAccount.setAuthenticationStatus(tbAccount1.getAuthenticationStatus());
                tbAccount.setBalanceNumber(balanceList.get(0).getBalance());
                //查询码号
                TbAccountCodeNumber tbAccountCodeNumber = new TbAccountCodeNumber();
                tbAccountCodeNumber.setAccountId(accountId);
                List<TbAccountCodeNumber> tbAccountCodeNumberList = tbAccountCodeNumberService.queryAll(tbAccountCodeNumber);
                if (CollectionUtils.isEmpty(tbAccountCodeNumberList)) {
                    tbAccount.setTbAccountCodeNumber(tbAccountCodeNumberList);
                } else {
                    tbAccount.setTbAccountCodeNumber(tbAccountCodeNumberList);
                }
                //今日发送量
                tbAccount.setAccountId(accountId);
                //成功  //失败                  //未知

                // tbAccount.setTodaySendSum(tbAccount.getTodaySendSuccess() + tbAccount.getTodaySendFail() + tbAccount.getTodaySendUnKnow());
                //return Msg.returnSuccessMsg(tbAccount);
                return Msg.returnSuccessMsg("查询成功", tbAccount);
            } catch (Exception e) {
                logger.error("查询异常");
                return Msg.returnErrorMsg("查询异常");
            }


        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg creataAccountId() {
        String accountId = TemplateIdService.DelegateTemplateIdService.getInstance().newTemplateId();//SnowFlakeIdUtil.getInstance().nextId();
        Boolean result = dbUserService.checkAccountId(accountId);
        if (result) {
            return Msg.returnSuccessMsg(CommonMessageUtil.OPERATION_SUCCESS, accountId);
        } else {
            return Msg.returnSuccessMsg(CommonMessageUtil.OPERATION_FAIL);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg updateAccountCodeNumber(String accountId, int[] codetype, String[] codeNumber) {
        //通过更新码号表
        //码号验证重复
        if (codeNumber != null && codeNumber.length > 0 && codetype != null && codetype.length > 0) {
            List<TbAccountCodeNumber> tbAccountCodeNumberList = new LinkedList<>();
            for (int index = 0; index < codeNumber.length; index++) {
                TbAccountCodeNumber t = new TbAccountCodeNumber();
                if (codetype[index] != 0) {
                    t.setCodeType(codetype[index]);

                }
                if (StringUtils.isNotBlank(codeNumber[index])) {
                    t.setCodeNumber(codeNumber[index]);

                }
                t.setAccountId(accountId);
                t.setStatus(1);
                tbAccountCodeNumberList.add(t);
            }
            for (TbAccountCodeNumber tbAccountCodeNumber : tbAccountCodeNumberList) {
                Boolean result = tbAccountCodeNumberService.checkCodeNumber(tbAccountCodeNumber);
                if (!result) {
                    return Msg.returnErrorMsg("码号重复：{}", tbAccountCodeNumber.getCodeNumber());
                }
            }
            try {
                //删除之前的码号数据
                tbAccountCodeNumberService.deleteByAccountId(accountId);
                //验证通过不重复则提交入库
                tbAccountCodeNumberService.insertCodeNumber(tbAccountCodeNumberList);
                return Msg.returnSuccessMsg("处理成功");
            } catch (Exception e) {
                e.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Msg.returnErrorMsg("处理异常：{}", e);
            }

        } else {
            return Msg.returnErrorMsg("传入数据为空");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg updateAccountDelegate(String accountId, String delegate) {
        TbAccount tbAccount = new TbAccount();
        tbAccount.setCompany(delegate);
        List<TbAccount> list = tbAccountService.queryAll(tbAccount);
        if (CollectionUtils.isEmpty(list)) {
            return Msg.returnErrorMsg("没有该名称代理商");
        } else {
            if (list.size() > 1) {
                return Msg.returnErrorMsg("同名代理商，请核对");
            } else {
                TbAccount tbAccount1 = new TbAccount();
                tbAccount1.setAccountId(accountId);
                tbAccount1.setParentAccountId(list.get(0).getParentAccountId());
                tbAccountService.update(tbAccount1);
                return Msg.returnSuccessMsg("修改成功");
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg insertUserInfo(String company, String username, String remark, Integer status, String parentAccountId) {
        if (StringUtils.isNotBlank(parentAccountId)) {
            // true 重复
            Boolean unCheck = dbUserService.checkUserName(username);
            if (unCheck) {
                return Msg.returnErrorMsg("该账号已注册，请换一个手机号码");
            }
        }
        if (StringUtils.isNotBlank(username)) {
            // true 重复
            Boolean unCheck = dbUserService.checkUserName(username);
            if (unCheck) {
                return Msg.returnErrorMsg("该账号已注册，请换一个手机号码");
            }
        }

/*        Boolean cnCheck = dbUserService.checkCompanyName(company);
        if (cnCheck) {
            return Msg.returnErrorMsg("企业名称重复");
        }*/
        //
        boolean result = false;
        String accountId = "";
        while (true) {
            try {
                accountId = redisLock.getAccountId();
            } catch (Throwable throwable) {
                logger.error("账号id获取失败，{},{}", throwable, throwable.getMessage());
                return Msg.returnErrorMsg("redis 链接失败");
            }
//            accountId = TemplateIdService.DelegateTemplateIdService.getInstance().newTemplateId();
            result = dbUserService.checkAccountId(accountId);
            if (result) {
                break;//不重复
            }
        }
       /* Boolean result = dbUserService.checkAccountId(accountId);
        if (result) {
            logger.info("accountId不重复：{}", accountId);
        } else {
            while (true) {
                accountId = SnowFlakeIdUtil.getInstance().nextId();
                result = dbUserService.checkAccountId(accountId);
                if (result) {
                    break;//不重复
                }
            }
        }*/
        DbUser dbUser = new DbUser();

        /* Boolean result = BCrypt.checkpw("123", bcrypt);;*/
        dbUser.setAccountId(accountId);
        String stal = BCrypt.gensalt();
        String password = RandomUtil.createRandomString();
        String bcrypt = BCrypt.hashpw(password, stal);//加盐加密
        dbUser.setSalt(stal);
        dbUser.setPassword(bcrypt);
        dbUser.setUsername(username);
        dbUser.setStatus(status);
        if (StringUtils.isNotBlank(company)) {
            dbUser.setContactsName(company);
            if (StringUtils.isNotBlank(remark)) {
                dbUser.setRemark(remark);
            }
        }

        //新增公司
        TbAccount tbAccount = new TbAccount();
//        String serial = redisLock.getSerialId();//
        tbAccount.setAccountId(accountId);

        tbAccount.setAuthenticationStatus("2");
        tbAccount.setCreateTime(new Date());
        if (StringUtils.isNotBlank(parentAccountId)) {
            tbAccount.setParentAccountId(parentAccountId);
        }
        if (StringUtils.isNotBlank(company)) {
            tbAccount.setCompany(company);
            //备注
            if (StringUtils.isNotBlank(remark)) {
                tbAccount.setRemark(remark);
            }
        } else {
            if (StringUtils.isNotBlank(remark)) {
                logger.info(">>>用户注册手动输入密码：{}", remark);
                password = remark;
                //字段复用
                bcrypt = BCrypt.hashpw(remark, stal);//加盐加密
                dbUser.setPassword(bcrypt);
            }
        }

        tbAccount.setMobile(username);
        dbUser = dbUserService.insert(dbUser);
        //新增用户账户表
        String serial = null;
        try {
//            tbAccountService.insert(tbAccount);
            //客户编号冲突
            while (true) {
                serial = redisLock.getSerialId();//
                tbAccount.setSerial(serial);
                TbAccount tbAccounts = new TbAccount();
                tbAccounts.setSerial(serial);
                List<TbAccount> listcheck = tbAccountService.queryAll(tbAccounts);
                if (listcheck != null && listcheck.size() == 0) {
                    tbAccount.setSerial(serial);
                    tbAccount.setMobile(username);
                    tbAccountService.insert(tbAccount);
                    break;
                }
            }
        } catch (Exception sql) {
            logger.info("账号信息入库异常：e:{},message:{}", sql.getMessage(), sql);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Msg.returnErrorMsg("新增信息入库异常");
        }

        //新增权限关联关系表
        DbUserRole dbUserRole = new DbUserRole();
        dbUserRole.setUserId(dbUser.getId());
        dbUserRole.setRoleId(RoleConstants.getRegist_rule());
        dbUserRole.setAppid("0");
        //新增用户余额
        TbAccountBalance tbAccountBalance = new TbAccountBalance();
        tbAccountBalance.setBalance(BalanceConstants.getRegistGiveVedioBalance());
        tbAccountBalance.setStatus(1);
        tbAccountBalance.setAcctId(dbUser.getAccountId());
        tbAccountBalance.setSumBalance(BalanceConstants.getRegistGiveVedioBalance());
        tbAccountBalance.setCreateTime(new Date());

        /**
         * 普通短信账户余额
         */
        TbAccountBalance tbAccountSmsBalance = new TbAccountBalance();
        tbAccountSmsBalance.setBalance(BalanceConstants.getRegistGiveSmsBalance());
        tbAccountSmsBalance.setStatus(1);
        tbAccountSmsBalance.setAcctId(dbUser.getAccountId());
        tbAccountSmsBalance.setSumBalance(BalanceConstants.getRegistGiveSmsBalance());
        tbAccountSmsBalance.setCreateTime(new Date());
        tbAccountSmsBalance.setBusi(1);
        //dbUser.getTbAccount().setBalanceNumber(0L);
        dbUser.setPassword(password);
        dbUser.setTbAccount(tbAccount);
        dbUser.setSerial(serial);
        try {
            dbUserRoleService.insert(dbUserRole);
            accountBalanceService.insert(tbAccountBalance);
            accountSMSBalanceService.insert(tbAccountSmsBalance);
            prepayService.charge(BalanceType.getSumSmsBalance() + accountId, BalanceConstants.getRegistGiveSmsBalance());
            prepayService.charge(BalanceType.getSumVedioBalance() + accountId, BalanceConstants.getRegistGiveVedioBalance());
            prepayService.charge(BalanceType.getSmsBalance() + accountId, BalanceConstants.getRegistGiveSmsBalance());
            prepayService.charge(BalanceType.getVedioBalance() + accountId, BalanceConstants.getRegistGiveVedioBalance());
            return Msg.returnSuccessMsg(CommonMessageUtil.OPERATION_SUCCESS, dbUser);
        } catch (Exception e) {
            logger.error("数据入库异常 message {} e {}", e.getMessage(), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Msg.returnErrorMsg("数据入库异常", e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg checkCompanyInfo(String accountId, Integer authenticationStatus, String remark, int[] codetype, String[] codeNumber, String username) {
        DbExaminationApproval dbExaminationApproval = new DbExaminationApproval();
        dbExaminationApproval.setApplicantId(accountId);//企业id
        List<DbExaminationApproval> list = dbExaminationApprovalService.queryAll(dbExaminationApproval);
        if (CollectionUtils.isEmpty(list)) {
            //第一次
        } else {
            dbExaminationApproval = list.get(0);
        }


        if (authenticationStatus != null && authenticationStatus == 1) {
            for (int i = 0; codeNumber != null && i < codeNumber.length; i++) {
                if (RandomUtil.isNumeric(codeNumber[i])) {

                } else {
                    return Msg.returnErrorMsg(codeNumber[i] + "码号只能为数字！！！");
                }
            }
            //通过更新码号表
            //码号验证重复
            /*
            if (codeNumber != null && codeNumber.length > 0 && codetype != null && codetype.length > 0) {
                List<TbAccountCodeNumber> tbAccountCodeNumberList = new LinkedList<>();
                for (int index = 0; index < codeNumber.length; index++) {
                    TbAccountCodeNumber t = new TbAccountCodeNumber();
                    if (codetype[index] != 0) {
                        t.setCodeType(codetype[index]);

                    }
                    if (StringUtils.isNotBlank(codeNumber[index])) {
                        t.setCodeNumber(codeNumber[index]);

                    }
                    t.setAccountId(accountId);
                    t.setStatus(1);
                    tbAccountCodeNumberList.add(t);
                }
                for (TbAccountCodeNumber tbAccountCodeNumber : tbAccountCodeNumberList) {
                    Boolean result = tbAccountCodeNumberService.checkCodeNumber(tbAccountCodeNumber);
                    if (!result) {
                        return Msg.returnErrorMsg("码号重复：" + tbAccountCodeNumber.getCodeNumber(), tbAccountCodeNumber.getCodeNumber());
                    }
                }
             */
            try {
                List<TbAccountCodeNumber> tbAccountCodeNumberList = buildChannelInfo();
                for (TbAccountCodeNumber tbAccountCodeNumber : tbAccountCodeNumberList) {
                    tbAccountCodeNumber.setAccountId(accountId);
                    tbAccountCodeNumber.setCreater(username);
                }
                //删除之前的码号数据
                tbAccountCodeNumberService.deleteByAccountId(accountId);
                //验证通过不重复则提交入库
                tbAccountCodeNumberService.insertCodeNumber(tbAccountCodeNumberList);
            } catch (Exception e) {
                logger.error("入网审核处理异常", e);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Msg.returnErrorMsg("处理异常：{}", e.getMessage());
            }

        }
        /*
        } else if (authenticationStatus != null && authenticationStatus == 2) {
            if (StringUtils.isNotBlank(remark)) {
                dbExaminationApproval.setContent(remark);
            }
        }
         */
        DbUser dbUser = dbUserService.quareUserInfoByUsername(username);
        if (dbUser == null) {
            return Msg.returnErrorMsg("用户信息未查询到");
        }
        dbExaminationApproval.setApproverId(dbUser.getId());
        dbExaminationApproval.setResult(authenticationStatus);
        dbExaminationApproval.setStatus(1);
        try {
            if (dbExaminationApproval.getId() != null && dbExaminationApproval.getId() > 0) {
                //update
                dbExaminationApprovalService.update(dbExaminationApproval);
            } else {

                dbExaminationApprovalService.insert(dbExaminationApproval);
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Msg.returnErrorMsg("处理异常：{}", e);
        }
        //修改账户信息
        TbAccount tbAccount = new TbAccount();
        tbAccount.setAccountId(accountId);
        tbAccount.setAuthenticationStatus(String.valueOf(authenticationStatus));
        try {
            tbAccountService.updateUserByAccount(tbAccount.getAccountId(), dbUser.getId(), 39);
            return tbAccountService.update(tbAccount);

        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Msg.returnErrorMsg("处理异常：{}", e);
        }

        //
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg selectSunAccountByAccountId(String companyName, String authenticationStatus, Integer pageNum, Integer pageSize, String accountId) {
        TbAccountBalance tbAccountBalance = new TbAccountBalance();
        tbAccountBalance.setAcctId(accountId);
        //当前账户余额
        List<TbAccountBalance> list = accountBalanceService.queryAll(tbAccountBalance);
        //客户总余额
        Long sunBalance = accountBalanceService.countBalanceBySunAccount(tbAccountBalance);
        //客户本周发送量
        Long sumSend = accountBalanceService.countSendNumberBySunAccount(tbAccountBalance);

        Map<String, Object> map = new HashMap<>();
        map.put("balance", list.get(0).getBalance());
        map.put("sunBalance", sunBalance);
        map.put("sumSend", sumSend);
        TbAccount dbUser = new TbAccount();

        if (StringUtils.isNotBlank(companyName)) {
            dbUser.setCompany(companyName);
        }
        if (StringUtils.isNotBlank(authenticationStatus)) {
            dbUser.setAuthenticationStatus(authenticationStatus);
        }
        if (StringUtils.isNotBlank(accountId)) {
            dbUser.setAccountId(accountId);
        }

        try {
            PageHelper.startPage(pageNum, pageSize);
            List<TbAccount> tbAccountList = tbAccountService.selectSunAccount(dbUser);
            PageInfo<TbAccount> pageInfo = new PageInfo<>(tbAccountList);
            map.put("pageInfo", pageInfo);
            return Msg.returnSuccessMsg("查询成功", map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("用户管理查询异常：{}" + e);
            return Msg.returnErrorMsg("用户管理查询异常");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg updatetUserInfo(TbAccount tbAccount) {
        return tbAccountService.update(tbAccount);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg updateAccountAgreementByAccountId(TbAccount tbAccount) {
        return tbAccountService.update(tbAccount);
    }

    @Override
    public List<TbAccount> getAccountInfoByAccountIds(String[] accountIds) {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(accountIds));

        return tbAccountMapper.getAccountInfoByAccountIds(list);
    }

    private List<TbAccountCodeNumber> buildChannelInfo() {
        int mobile = 0;
        int unicom = 0;
        int telecom = 0;

        // 从数据库查询到通道信息
        List<TbChannelInfo> tbChannelInfos = tbChannelInfoMapper.selectDefaultChannelInfo(0);

        List<TbAccountCodeNumber> tbAccountCodeNumberList = new LinkedList<>();
        // 把查找到的信息都放到list中,且必须为三网
        for (TbChannelInfo tbChannelInfo : tbChannelInfos) {
            TbAccountCodeNumber tbAccountCodeNumber = new TbAccountCodeNumber();
            String operator = tbChannelInfo.getTbChannelType().getOperators();
          /*  switch (operator) {
                case GpdConstants.OP_MOBILE:
                    mobile += 1;
                    if (mobile >= 2) {
                        continue;
                    }
                    tbAccountCodeNumber.setCodeType(1);
                    break;
                case GpdConstants.OP_UNICOM:
                    unicom += 1;
                    if (unicom >= 2) {
                        continue;
                    }
                    tbAccountCodeNumber.setCodeType(2);
                    break;
                case GpdConstants.OP_TELECOM:
                    telecom += 1;
                    if (telecom >= 2) {
                        continue;
                    }
                    tbAccountCodeNumber.setCodeType(3);
                    break;
                default:
            }*/
            String ciId = tbChannelInfo.getId();
            Integer rate = tbChannelInfo.getRate();
            tbAccountCodeNumber.setCiId(ciId);
            tbAccountCodeNumber.setRate(rate);
            tbAccountCodeNumber.setStatus(1);
            tbAccountCodeNumber.setCreateTime(new Date());

            // 生成唯一的扩展号
            if (tbChannelInfo.getExtensible() == 1) {
                String codeNumber = buildExtCode(tbChannelInfo.getCtId(), tbChannelInfo.getExtLength());
                tbAccountCodeNumber.setCodeNumber(codeNumber);
            }
            tbAccountCodeNumberList.add(tbAccountCodeNumber);
        }

        // 如果没有值的话,需要生成一个空的信息,ciId字段不允许为空这里可能会导致报错的
        for (int i = tbAccountCodeNumberList.size(); i < 3; i++) {
            TbAccountCodeNumber tbAccountCodeNumber = new TbAccountCodeNumber();
            if (mobile == 0) {
                mobile += 1;
                tbAccountCodeNumber.setCodeType(1);
            } else if (unicom == 0) {
                unicom += 1;
                tbAccountCodeNumber.setCodeType(2);
            } else if (telecom == 0) {
                telecom += 1;
                tbAccountCodeNumber.setCodeType(3);
            }
            tbAccountCodeNumberList.add(tbAccountCodeNumber);
        }

        return tbAccountCodeNumberList;
    }

    /**
     * 生成扩展号
     *
     * @return
     */
    private String buildExtCode(String ciId, int extLength) {
        StringBuilder maxValue = new StringBuilder("8");
        StringBuilder vmsCode = new StringBuilder();

        for (int i = 1; i < extLength; i++) {
            maxValue.append(9);
        }

        do {
            int randomNum = (int) (Math.random() * (new Integer(maxValue.toString()) + 1));
            String randomNumStr = String.valueOf(randomNum);
            for (int i = randomNumStr.length(); i < extLength; i++) {
                vmsCode.append("0");
            }
            vmsCode.append(randomNumStr);

            int count = tbAccountCodeNumberService.statisticalVmsCode(vmsCode.toString(), ciId);
            if (count == 0) {
                return vmsCode.toString();
            }
        } while (true);
    }
}