package com.umpay.rms.gpd.user.controller;

import cn.hutool.extra.spring.SpringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.user.api.entity.DbUser;
import com.umpay.rms.gpd.user.api.entity.DbUserLog;
import com.umpay.rms.gpd.user.service.DbUserLogService;
import com.umpay.rms.gpd.user.service.DbUserService;
import com.umpay.rms.gpd.user.util.ip.IpUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (DbUserLog)表控制层
 *
 * @author makejava
 * @since 2020-06-05 13:21:05
 */
@Api(value = "登录日志",tags={"登录日志"})

@RestController
@RequestMapping("dbUserLog")
public class UserLogController {
    private  static Logger logger = LoggerFactory.getLogger( UserLogController.class );
    /**
     * 服务对象
     */
    @Resource
    private DbUserLogService dbUserLogService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;



    @ApiOperation(value = "登录日志查询")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pageNum", value = "第几页", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(name = "pageSize", value = "每页几条", paramType = "query", dataType = "String", required = false)})
    @ApiResponses(value = {@ApiResponse(code = 500, message = "后台异常"),
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 401, message = "请求无权限"),
            @ApiResponse(code = 402, message = "请求未授权"),
            @ApiResponse(code = 403, message = "请求被禁止"),
            @ApiResponse(code = 404, message = "请求页面找不到"),
            @ApiResponse(code = 405, message = "请求方式不正确")})
    @GetMapping("/getUserLog")
    public Msg getUserLog(
            @RequestAttribute(value = "username") String username,
            @RequestParam(value = "pageNum",required = true,defaultValue = "1") Integer pageNum ,
            @RequestParam(value = "pageSize" ,required = false,defaultValue = "10") Integer pageSize){
        DbUserLog dbUserLog = new DbUserLog();
            dbUserLog.setUsername(username);
        try{
            PageHelper.startPage(pageNum,pageSize);
            List<DbUserLog> list = dbUserLogService.queryAll(dbUserLog);
            PageInfo<DbUserLog> pageInfo =  new PageInfo<DbUserLog>(list);

            return Msg.returnSuccessMsg("查询成功",pageInfo);
        }catch (Exception e){
            logger.info("查询数据异常：{}",e);
            return Msg.returnErrorMsg("数据查询异常");
        }

    }
    public static  void logInfo(String username){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        DbUserLog dbUserLog = new DbUserLog();
        //登录
        String ipAddress = IpUtil.getIpAddress(request);
        DbUserLogService dbUserLogService = SpringUtil.getBean(DbUserLogService.class);
        DbUserService dbUserService = SpringUtil.getBean(DbUserService.class);
        DbUser dbUser = new DbUser();
        dbUser.setUsername(username);
        List<DbUser> userList = dbUserService.queryAll(dbUser);
        if (CollectionUtils.isEmpty(userList)) {
            logger.info("用户账户信息未查到到");
        } else {
            dbUserLog.setUid(userList.get(0).getId());
        }
        dbUserLog.setStatus(1);
        dbUserLog.setUsername(dbUser.getUsername());
        dbUserLog.setIp(ipAddress);
        dbUserLogService.insert(dbUserLog);
    }

}