package com.umpay.rms.gpd.user.controller;

import cn.hutool.core.collection.CollectionUtil;


import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.user.api.entity.DbRolePermission;
import com.umpay.rms.gpd.user.util.CommonMessageUtil;
import com.umpay.rms.gpd.user.service.DbRolePermissionService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限表(DbRolePermission)表控制层
 *
 * @author makejava
 * @since 2020-06-02 14:51:15
 */
@Api(value = "角色权限处理",tags={"角色权限处理"})

@RestController
@RequestMapping("dbRolePermission")
public class RolePermissionController {
    Logger logger = LoggerFactory.getLogger( this.getClass() );
    /**
     * 服务对象
     */
    @Resource
    private DbRolePermissionService dbRolePermissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    /*@GetMapping("selectOne")*/
    public DbRolePermission selectOne(Long id) {
        return this.dbRolePermissionService.queryById(id);
    }
    /**
     * 用户名获取用户详细信息
     *
     * @param dbRolePermission
     * @return
     */
    @PostMapping(value = "selectDbRolePermissionInfoByDbRolePermissionName")
    public Msg selectDbRolePermissionInfoByDbRolePermissionName(@RequestBody DbRolePermission dbRolePermission) {
        List<DbRolePermission> list =  dbRolePermissionService.queryAll(dbRolePermission);
        if(CollectionUtil.isEmpty(list)){
            return Msg.returnSuccessMsg(CommonMessageUtil.OPERATION_SUCCESS,list);
        }else{
            return Msg.returnErrorMsg("查询为空");
        }
    }

    @PostMapping(value = "insertUserRole")
    public Msg insertUserRole() {
        DbRolePermission dbRolePermission = new DbRolePermission();
        dbRolePermission.setRoleId(37);
        List<DbRolePermission> list = dbRolePermissionService.queryAll(dbRolePermission);
        for(DbRolePermission dbRolePermission1 : list){
            dbRolePermission1.setRoleId(39);
            dbRolePermissionService.insert(dbRolePermission1);
        }
        return null;
    }


    @PostMapping("insertDbRolePermissionInfo")
    public Msg insertDbRolePermissionInfo(@RequestBody DbRolePermission dbDbRolePermission) {
        dbDbRolePermission = dbRolePermissionService.insert(dbDbRolePermission);
        if (dbDbRolePermission.getId() > 0) {
            return Msg.returnSuccessMsg(CommonMessageUtil.OPERATION_SUCCESS);
        } else {
            return Msg.returnSuccessMsg(CommonMessageUtil.OPERATION_FAIL);
        }
    }

    @PostMapping("updatetDbRolePermissionInfo")
    public Msg updatetDbRolePermissionInfo(@RequestBody DbRolePermission dbDbRolePermission) {
        return dbRolePermissionService.update(dbDbRolePermission);
    }

    @PostMapping("delettDbRolePermissionInfo/{id}")
    public Msg delettDbRolePermissionInfo(@PathVariable(value = "id", required = true) Long id) {
        Boolean b =  dbRolePermissionService.deleteById(id);
        if(b){
            return Msg.returnSuccessMsg(CommonMessageUtil.OPERATION_SUCCESS);
        } else {
            return Msg.returnSuccessMsg(CommonMessageUtil.OPERATION_FAIL);
        }
    }


}