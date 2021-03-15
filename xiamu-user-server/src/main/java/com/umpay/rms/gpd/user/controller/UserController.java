package com.umpay.rms.gpd.user.controller;

import cn.hutool.core.collection.CollectionUtil;


import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.internal.dto.ResponseResult;
import com.umpay.rms.gpd.internal.util.moblie.MobileUtil;


import com.umpay.rms.gpd.user.api.entity.DbUser;
import com.umpay.rms.gpd.user.api.entity.TbAccount;
import com.umpay.rms.gpd.user.api.request.UserForgotPasswordRequest;
import com.umpay.rms.gpd.user.api.request.UserInfoRequest;
import com.umpay.rms.gpd.user.api.request.UserRegisterRequest;
import com.umpay.rms.gpd.user.service.DbUserService;
import com.umpay.rms.gpd.user.service.LoginService;
import com.umpay.rms.gpd.user.service.TbAccountService;
import com.umpay.rms.gpd.user.util.CommonParamsUtil;
import com.umpay.rms.gpd.user.util.RandomUtil;
import com.umpay.rms.gpd.user.util.requset.RequsetUtil;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * (DbUser)表控制层
 *
 * @author makejava
 * @since 2020-06-02 14:51:17
 */

@Api(value = "用户管理", tags = {"用户管理"})
@ApiResponses(value = {@ApiResponse(code = 500, message = "后台异常"),
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 401, message = "请求无权限"),
        @ApiResponse(code = 402, message = "请求未授权"),
        @ApiResponse(code = 403, message = "请求被禁止"),
        @ApiResponse(code = 404, message = "请求页面找不到"),
        @ApiResponse(code = 405, message = "请求方式不正确")})
@RestController
@RequestMapping("dbUser")
public class UserController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 服务对象
     */
    @Resource
    private DbUserService dbUserService;
    @Resource
    private TbAccountService tbAccountService;
    @Resource
    private LoginService loginService;


    /**
     * 用户名获取用户详细信息
     *
     * @param username
     * @return
     */


    @ApiOperation(value = "获取当前账号信息")
    @PostMapping(value = "selectUserInfoByUserName")
    public Msg selectUserInfoByUserName(@RequestAttribute(value = "username") String username) {
        return dbUserService.selectUserInfoByUserName(username);
    }


    @ApiOperation(value = "修改密码")
    @ApiImplicitParams(value = {
            // @ApiImplicitParam(name = "id", value = "账号id", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "password", value = "原密码", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "newPassword", value = "新密码", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "newPasswordCheck", value = "确认密码", paramType = "query", dataType = "String", required = true),
    })
    @ApiResponses(value = {@ApiResponse(code = 500, message = "后台异常"),
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 401, message = "请求无权限"),
            @ApiResponse(code = 402, message = "请求未授权"),
            @ApiResponse(code = 403, message = "请求被禁止"),
            @ApiResponse(code = 404, message = "请求页面找不到"),
            @ApiResponse(code = 405, message = "请求方式不正确")})
    @PostMapping("updatetUserPassword")
    public Msg updatetUserPassword(
            @RequestAttribute(value = "userId") Integer id,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "newPassword", required = true) String newPassword,
            @RequestParam(value = "newPasswordCheck", required = true) String newPasswordCheck) {
        if (newPassword.equals(newPasswordCheck)) {
            DbUser dbUser = new DbUser();
            dbUser.setId(id);
            List<DbUser> list = dbUserService.queryAll(dbUser);
            if (CollectionUtil.isEmpty(list)) {
                logger.info("用户信息为空");
                return Msg.returnErrorMsg("用户信息为空");
            } else {
                Boolean result = BCrypt.checkpw(password, list.get(0).getPassword());
                if (result) {
                    if (newPassword.length() >= 6 && newPassword.length() <= 20) {
                        if (newPassword.matches("^.*[a-zA-Z]+.*$") && newPassword.matches("^.*[0-9]+.*$")) {
                            //加盐加密
                            String bcrypt = BCrypt.hashpw(newPassword, list.get(0).getSalt());
                            dbUser.setPassword(bcrypt);
                            return dbUserService.update(dbUser);
                        } else {
                            return Msg.returnErrorMsg("密码必须同时包含字母和数字");
                        }
                    }
                    return Msg.returnErrorMsg("密码格式不符合规范");

                } else {
                    logger.info("原始密码错误");
                    return Msg.returnErrorMsg("原始密码错误");
                }
            }
        } else {
            logger.info("两次密码不一致");
            return Msg.returnErrorMsg("两次密码不一致");
        }
    }


    @ApiOperation(value = "管理员重置密码")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "accountId", value = "账户id", paramType = "query", dataType = "String", required = true),
    })
    @ApiResponses(value = {@ApiResponse(code = 500, message = "后台异常"),
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 401, message = "请求无权限"),
            @ApiResponse(code = 402, message = "请求未授权"),
            @ApiResponse(code = 403, message = "请求被禁止"),
            @ApiResponse(code = 404, message = "请求页面找不到"),
            @ApiResponse(code = 405, message = "请求方式不正确")})
    @PostMapping("adminUpdateUserPassword")
    public Msg adminUpdateUserPassword(
            @RequestParam(value = "accountId") String accountId) {
        TbAccount tbAccount = tbAccountService.queryById(accountId);
        if (tbAccount == null) {
            return Msg.returnErrorMsg("未查询到该账号");
        }
        DbUser dbUser = new DbUser();
        dbUser.setUsername(tbAccount.getMobile());
        List<DbUser> list = dbUserService.queryAll(dbUser);
        if (CollectionUtil.isEmpty(list)) {
            logger.info("用户信息为空");
            return Msg.returnErrorMsg("用户信息为空");
        } else {
            dbUser = list.get(0);
            String stal = dbUser.getSalt();
            String password = RandomUtil.createRandomString();
            String bcrypt = BCrypt.hashpw(password, stal);//加盐加密
            dbUser.setPassword(bcrypt);
            dbUser.setTbAccount(tbAccount);
            Msg msg = dbUserService.update(dbUser);
            if (msg.isSuccess()) {
                dbUser.setPassword(password);
                msg.setData(dbUser);
            }
            return msg;
        }

    }

    /**************************************新增方法*******************************************/
    @ApiOperation(value = "忘记密码")
    @PostMapping("forgotPassword")
    public ResponseResult<String> forgotPassword(
            @RequestBody UserForgotPasswordRequest userInfoRequest/*, HttpServletRequest request*/) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();


        if (StringUtils.isBlank(userInfoRequest.getMobile())) {
            return ResponseResult.fail("用户名不合法");
        } else {
/*            Boolean result = MobileUtil.checkMobile(userInfoRequest.getMobile());
            if (!result) {
                return ResponseResult.fail("手机号非法");
            }*/
        }
        DbUser dbUser = new DbUser();
        dbUser.setUsername(userInfoRequest.getMobile());
        List<DbUser> dbUserList = dbUserService.queryAll(dbUser);
        if (CollectionUtils.isEmpty(dbUserList)) {
            return ResponseResult.fail("用户信息不存在");
        } else {

            if (StringUtils.isBlank(userInfoRequest.getPassword())) {
                return ResponseResult.fail("新密码不可以为空");
            } else {
                if (StringUtils.isBlank(userInfoRequest.getConfrimPassword())) {
                    return ResponseResult.fail("确认密码不可以为空");
                } else {
                    //解密密码
                    String jsonId = (String) RequsetUtil.getJessionId(request, CommonParamsUtil.getJessionid()).getData();

                    Msg result = loginService.getPasswordByPrivatekey(jsonId, userInfoRequest.getPassword(), userInfoRequest.getConfrimPassword());
                    // RSA解密
                    if (result.isFail()) {
                        return ResponseResult.fail(result.getMessage());
                    } else {
                        dbUser = dbUserList.get(0);
                        String stal = dbUser.getSalt();
                        String bcrypt = BCrypt.hashpw(result.getMessage(), stal);//加盐加密
                        dbUser.setPassword(bcrypt);
                        Msg msg = dbUserService.update(dbUser);
                        if (msg.isSuccess()) {
                            return ResponseResult.success("密码修改成功");
                        } else {
                            return ResponseResult.fail(msg.getMessage());
                        }
                    }
                }
            }
        }
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("onlineRegister")
    public ResponseResult<String> onlineRegister(
            @RequestBody UserRegisterRequest userInfoRequest/*, HttpServletRequest request*/) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        if (StringUtils.isBlank(userInfoRequest.getMobile())) {
            return ResponseResult.fail("手机号不合规");
        } else {
            Boolean result = MobileUtil.checkMobile(userInfoRequest.getMobile());
            if (!result) {
                return ResponseResult.fail("手机号非法");
            }
        }
        if (StringUtils.isBlank(userInfoRequest.getSmsCode())) {
            return ResponseResult.fail("短信验证码不可为空");
        }
        Msg msg = loginService.checkSmsCodeRegister(userInfoRequest.getMobile(), userInfoRequest.getSmsCode());
//        Msg msg = Msg.returnSuccessMsg("");// loginService.checkSmsCodeForgot(userInfoRequest.getMobile(), userInfoRequest.getSmsCode());


        if (msg.isSuccess()) {
            Msg decryp = loginService.decryptionPassword(request, userInfoRequest.getPassword());
            userInfoRequest.setPassword(decryp.getMessage());
            Boolean result = RandomUtil.checkPassword(userInfoRequest.getPassword());
            if (result) {
                return dbUserService.onlineRegister(userInfoRequest);
            } else {
                return ResponseResult.fail("密码不符合规范");
            }
        } else {
            return ResponseResult.fail(msg.getMessage());
        }


    }


    /**
     * 传入用户名获取用户详细信息
     *
     * @param dbUser
     * @return
     */


    @ApiOperation(value = "传入username获取当前账号信息")
    @PostMapping(value = "getUserInfoByUsername")
    public Msg selectUserInfo(@RequestBody DbUser dbUser) {
        if (StringUtils.isNotBlank(dbUser.getUsername())) {
            return dbUserService.selectUserInfoByUserName(dbUser.getUsername());
        }
        return Msg.returnErrorMsg("username不合法");
    }

    /**
     * 传入用户名获取用户详细信息
     *
     * @param request
     * @return
     */


    @ApiOperation(value = "传入username数组，获取账号信息")
    @PostMapping(value = "getUserInfos")
    public Msg getUserInfos(@RequestBody UserInfoRequest request) {

        if (request.getUsernames() != null && request.getUsernames().length > 0) {
            List<DbUser> list = new LinkedList<>();
            for (String username : request.getUsernames()) {
                if (StringUtils.isNotBlank(username)) {
                    DbUser dbUser = new DbUser();
                    dbUser.setUsername(username);
                    List<DbUser> result = dbUserService.queryAll(dbUser);
                    if (CollectionUtils.isEmpty(result)) {
                        //没有查到
                        return Msg.returnErrorMsg("username" + username + "未查询到数据");
                    } else {
                        list.add(result.get(0));
                    }
                } else {
                    //用户名为空
                    return Msg.returnErrorMsg("username有参数不合法");
                }
            }
            return Msg.returnSuccessMsg("查询成功", list);

        }

        return Msg.returnErrorMsg("username不合法");
    }

}

