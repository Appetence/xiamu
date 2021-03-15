package com.umpay.rms.gpd.user.controller;

import cn.hutool.core.collection.CollectionUtil;


import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.user.api.entity.DbPermission;
import com.umpay.rms.gpd.user.api.entity.DbRole;
import com.umpay.rms.gpd.user.util.CommonMessageUtil;
import com.umpay.rms.gpd.user.service.DbRolePermissionService;
import com.umpay.rms.gpd.user.service.DbRoleService;
import com.umpay.rms.gpd.user.service.DbUserRoleService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色表(DbRole)表控制层
 *
 * @author makejava
 * @since 2020-06-02 14:51:12
 */
@Api(value = "角色处理", tags = {"角色处理"})

@RestController
@RequestMapping("dbRole")
public class RoleController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 服务对象
     */
    @Resource
    private DbRoleService dbRoleService;
    @Resource
    private DbUserRoleService dbUserRoleService;
    @Resource
    private DbRolePermissionService dbRolePermissionService;



    @ApiOperation(value = "用户左侧菜单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", dataType = "String", required = true)
    })
    @ApiResponses(value = {@ApiResponse(code = 500, message = "后台异常"),
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 401, message = "请求无权限"),
            @ApiResponse(code = 402, message = "请求未授权"),
            @ApiResponse(code = 403, message = "请求被禁止"),
            @ApiResponse(code = 404, message = "请求页面找不到"),
            @ApiResponse(code = 405, message = "请求方式不正确")})
    @PostMapping("selectRolePermission")
    public Msg selectRolePermission(@RequestParam(value = "userId") Integer userId) {
        List<DbPermission> list = dbRolePermissionService.selectRolePermission(userId);
        return Msg.returnSuccessMsg("查询成功",list);
    }

    /**
     * 查询角色详细信息
     *
     * @param dbRole
     * @return
     */
    @PostMapping(value = "selectRoleByRole")
    public Msg selectRoleByRole(@RequestBody DbRole dbRole) {
        List<DbRole> list = dbRoleService.queryAll(dbRole);
        if (CollectionUtil.isEmpty(list)) {
            return Msg.returnSuccessMsg(CommonMessageUtil.OPERATION_SUCCESS, list);
        } else {
            return Msg.returnErrorMsg("查询为空");
        }
    }


    @PostMapping("insertRoleInfo")
    public Msg insertRoleInfo(@RequestBody DbRole dbRole) {
        dbRole = dbRoleService.insert(dbRole);
        if (dbRole.getId() > 0) {
            return Msg.returnSuccessMsg(CommonMessageUtil.OPERATION_SUCCESS);
        } else {
            return Msg.returnSuccessMsg(CommonMessageUtil.OPERATION_FAIL);
        }
    }

    @PostMapping("updatetRoleInfo")
    public Msg updatetRoleInfo(@RequestBody DbRole dbRole) {
        return dbRoleService.update(dbRole);
    }

    @PostMapping("delettRoleInfo/{id}")
    public Msg delettRoleInfo(@PathVariable(value = "id", required = true) Long id) {
        Boolean b = dbRoleService.deleteById(id);
        if (b) {
            return Msg.returnSuccessMsg(CommonMessageUtil.OPERATION_SUCCESS);
        } else {
            return Msg.returnSuccessMsg(CommonMessageUtil.OPERATION_FAIL);
        }
    }


}