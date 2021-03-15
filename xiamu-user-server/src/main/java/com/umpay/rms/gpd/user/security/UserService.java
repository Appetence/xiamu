package com.umpay.rms.gpd.user.security;





import com.umpay.rms.gpd.user.api.entity.DbUser;
import com.umpay.rms.gpd.user.api.entity.DbUserRole;
import com.umpay.rms.gpd.user.api.entity.TbAccount;
import com.umpay.rms.gpd.user.mapper.DbUserMapper;
import com.umpay.rms.gpd.user.security.consum.OAuthUser;
import com.umpay.rms.gpd.user.service.DbButtonService;
import com.umpay.rms.gpd.user.service.DbUserRoleService;
import com.umpay.rms.gpd.user.service.TbAccountService;
import com.umpay.rms.gpd.user.util.CommonMessageUtil;
import com.umpay.rms.gpd.user.util.rsa.RSAUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 用于加载用户信息
 */
@Component
public class UserService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private DbUserMapper dbUserMapper;
    @Resource
    private DbButtonService dbButtonService;

    @Resource
    private TbAccountService tbAccountService;
    @Resource
    private DbUserRoleService dbUserRoleService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DbUser dbUser = new DbUser();
        List<DbUser> findUserList = null;
        try {
            dbUser.setUsername(username);
            findUserList = dbUserMapper.selectUserByUser(dbUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<GrantedAuthority> grantedAuthoritys = new ArrayList<>();
        if (!CollectionUtils.isEmpty(findUserList)) {
            dbUser = findUserList.get(0);
            if (dbUser.getStatus() == 1) {
                DbUserRole dbUserRole = new DbUserRole();
                dbUserRole.setUserId(dbUser.getId());
                List<DbUserRole> dbUserRoleList = dbUserRoleService.queryAll(dbUserRole);
                //获取账户信息
                TbAccount tbAccount = new TbAccount();
                tbAccount.setAccountId(dbUser.getAccountId());
                List<TbAccount> tbAccountList = tbAccountService.queryAll(tbAccount);
                if (CollectionUtils.isEmpty(tbAccountList) || tbAccountList.get(0).getStatus() == 2) {
                    throw new InternalAuthenticationServiceException("该企业账户被冻结");
                }
                grantedAuthoritys = getBtnList(findUserList.get(0).getId());
                logger.debug("用户角色：{}", grantedAuthoritys.toString());
                return new OAuthUser(dbUser.getUsername(), dbUser.getPassword(), true, true, true,
                        true, grantedAuthoritys, dbUser.getStatus().toString()
                        , dbUser.getId().toString(), dbUser.getAccountId().toString(),
                        String.valueOf(dbUserRoleList.get(0).getRoleId()),dbUser.getCompany());
            } else {
                throw new InternalAuthenticationServiceException("该账户被冻结");
            }
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }

    public OAuthUser loadUserByUsernameAndSmscode(String username, String password) throws Exception {
        DbUser dbUser = new DbUser();
        dbUser.setUsername(username);
        List<DbUser> findUserList = dbUserMapper.selectRsaUserInfo(dbUser);

        List<GrantedAuthority> grantedAuthoritys = new ArrayList<>();
        if (!CollectionUtils.isEmpty(findUserList)) {
            dbUser = findUserList.get(0);
            if (dbUser.getStatus() == 1) {

                //密码解密
                // 生成密钥对
                String privateKey = redisTemplate.opsForValue().get(CommonMessageUtil.PRIVATE + username);
                // RSA解密
                String decryptData = RSAUtils.decrypt(password, RSAUtils.getPrivateKey(privateKey));
                // System.out.println("解密后内容:" + decryptData);
                String longtime = StringUtils.substringAfterLast(decryptData, ",");
                //  System.out.println("时间解密后:" + longtime);
                Boolean result = BCrypt.checkpw(decryptData, dbUser.getPassword());
                if (result) {
                    DbUserRole dbUserRole = new DbUserRole();
                    dbUserRole.setUserId(dbUser.getId());
                    List<DbUserRole> dbUserRoleList = dbUserRoleService.queryAll(dbUserRole);
                    //获取账户信息
                    TbAccount tbAccount = new TbAccount();
                    tbAccount.setAccountId(dbUser.getAccountId());
                    List<TbAccount> tbAccountList = tbAccountService.queryAll(tbAccount);
                    if (CollectionUtils.isEmpty(tbAccountList) || tbAccountList.get(0).getStatus() == 2) {
                        throw new InternalAuthenticationServiceException("该企业账户被冻结");
                    }
                   /* if (tbAccountList.get(0).getAuthenticationStatus().equals("1")) {
                        //7月7日，多表联查button
                        Set<String> btnList = dbButtonService.selectButtonByUserId(findUserList.get(0).getId());
                        for (String btn : btnList) {
                            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(btn);
                            grantedAuthoritys.add(grantedAuthority);
                        }
                    } else {
                        logger.info("用户身份为未认证用户", dbUser.getUsername());

                    }*/
                    grantedAuthoritys = getBtnList(findUserList.get(0).getId());
                    // logger.info("用户角色：{}",grantedAuthoritys.toString());
                    return new OAuthUser(dbUser.getUsername(), dbUser.getPassword(), true, true, true,
                            true, grantedAuthoritys, dbUser.getStatus().toString()
                            , dbUser.getId().toString(), dbUser.getAccountId().toString(),
                            String.valueOf(dbUserRoleList.get(0).getRoleId()),dbUser.getCompany());
                } else {
                    throw new InternalAuthenticationServiceException("该账户被冻结");

                }

            } else {
                throw new InternalAuthenticationServiceException("该账户被冻结");
            }
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }


    }

    public OAuthUser loadUserBySmscode(String username, String smscode) throws Exception {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(smscode)) {
            throw new UsernameNotFoundException("用户名或密码不正确");
        }


        DbUser dbUser = new DbUser();
        dbUser.setUsername(username);
        List<DbUser> findUserList = new LinkedList<DbUser>();
        try {
            findUserList = dbUserMapper.selectRsaUserInfo(dbUser);
        } catch (Exception sql) {
            logger.info("查询权限信息异常：message:{},e:{}", sql.getMessage(), sql);
            throw new RoleNotFoundException("查询权限信息异常");
        }

        List<GrantedAuthority> grantedAuthoritys = new ArrayList<>();
        if (!CollectionUtils.isEmpty(findUserList)) {
            dbUser = findUserList.get(0);
            if (dbUser.getStatus() == 1) {
                //redis中获取code比较
                String codeKey = CommonMessageUtil.SMS_CODE_FORGOT_PRE + username;
                if (redisTemplate.hasKey(codeKey)) {
                    try {
                        String code = redisTemplate.opsForValue().get(codeKey);
                        if (!smscode.equalsIgnoreCase(code)) {
                            throw new UsernameNotFoundException("短信验证码不正确");
                        } else {


                            DbUserRole dbUserRole = new DbUserRole();
                            dbUserRole.setUserId(dbUser.getId());
                            List<DbUserRole> dbUserRoleList = dbUserRoleService.queryAll(dbUserRole);
                            //获取账户信息
                            TbAccount tbAccount = new TbAccount();
                            tbAccount.setAccountId(dbUser.getAccountId());
                            List<TbAccount> tbAccountList = tbAccountService.queryAll(tbAccount);
                            if (CollectionUtils.isEmpty(tbAccountList) || tbAccountList.get(0).getStatus() == 2) {
                                throw new InternalAuthenticationServiceException("该企业账户被冻结");
                            }
                            grantedAuthoritys = getBtnList(findUserList.get(0).getId());
                            logger.debug("用户角色：{}", grantedAuthoritys.toString());
                            return new OAuthUser(dbUser.getUsername(), dbUser.getPassword(), true, true, true,
                                    true, grantedAuthoritys, dbUser.getStatus().toString()
                                    , dbUser.getId().toString(), dbUser.getAccountId().toString(),
                                    String.valueOf(dbUserRoleList.get(0).getRoleId()),dbUser.getCompany());
                        }
                    } catch (Throwable throwable) {
                        logger.error("redis 获取验证码异常：{} message {}", throwable.getMessage(), throwable);
                        throw new RuntimeException("短信验证码不正确");
                    }


                } else {
                    throw new UsernameNotFoundException("短信验证码不正确");
                }


            } else {
                throw new InternalAuthenticationServiceException("该账户被冻结");
            }
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }


    }
    private List<GrantedAuthority> getBtnList(Integer userId) {
        List<GrantedAuthority> grantedAuthoritys = new ArrayList<>();
        Set<String> btnList = dbButtonService.selectButtonByUserId(userId);
        for (String btn : btnList) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(btn);
            grantedAuthoritys.add(grantedAuthority);
        }
        return grantedAuthoritys;
    }
}



