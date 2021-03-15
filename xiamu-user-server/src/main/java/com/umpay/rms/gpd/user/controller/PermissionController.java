package com.umpay.rms.gpd.user.controller;



import com.umpay.rms.gpd.user.api.entity.DbPermission;
import com.umpay.rms.gpd.user.service.DbPermissionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 权限表(DbPermission)表控制层
 *
 * @author makejava
 * @since 2020-06-02 14:51:19
 */
@Api(value = "权限处理",tags={"权限处理"})

@RestController
@RequestMapping("dbPermission")
public class PermissionController {
    /**
     * 服务对象
     */
    @Resource
    private DbPermissionService dbPermissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    /*@GetMapping("selectOne")*/
    public DbPermission selectOne(Long id) {
        return this.dbPermissionService.queryById(id);
    }

}