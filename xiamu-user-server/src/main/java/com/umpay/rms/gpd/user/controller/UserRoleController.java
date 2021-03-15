package com.umpay.rms.gpd.user.controller;

import com.umpay.rms.gpd.user.service.DbUserRoleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户角色表(DbUserRole)表控制层
 *
 * @author makejava
 * @since 2020-06-02 14:45:40
 */
@Api(value = "用户角色处理",tags={"用户角色处理"})

@RestController
@RequestMapping("dbUserRole")
public class UserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private DbUserRoleService dbUserRoleService;


}