/*
 * 作者：xuda
 * 创建时间：18-4-28 上午11:10
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Permission;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.service.PermissionService;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/permission")
@Api(value = "PermissionController",description = "权限Api")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    private final static Logger logger = LoggerFactory.getLogger(PermissionController.class);

    /**
     * 查询权限列表
     * @return
     */
    @ApiOperation(value = "查询权限列表", notes = "查询权限列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result<Permission> getPermissions(@RequestParam(value = "page",required = false) Integer page,
                                             @RequestParam(value = "size",required = false) Integer size,
                                             @RequestParam(value = "sort_param", required = false, defaultValue = "createTime") String sortParam,
                                             @RequestParam(value = "sort_desc|asc", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return ResultUtil.success(permissionService.findAll(sort));
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return ResultUtil.success(permissionService.findAll(request));
        }
    }

    /**
     * 查询单个权限
     * @return
     */
    @ApiOperation(value = "查询单个权限", notes = "查询单个权限")
    @GetMapping(value = "/findOne/{id}")
    public Result<Permission> findOnePermission(@PathVariable("id") Long id) {
        logger.info("findOneProject");
        return ResultUtil.success(permissionService.findOne(id));
    }

    /**
     * 添加权限
     */
    @ApiOperation(value = "创建权限", notes = "根据permission对象创建权限")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result<Permission> addPermission(@RequestBody Permission permission) {

        return ResultUtil.success(permissionService.save(permission));
    }

    /**
     * 更新一个权限
     * @return
     */
    @ApiOperation(value = "更新权限", notes = "根据权限的id来更新权限信息")
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<Permission> updatePermission(@RequestBody Permission permission) {

        return ResultUtil.success(permissionService.save(permission));
    }

    /**
     * 删除权限
     * @param id
     */
    @ApiOperation(value = "删除权限", notes = "根据id删除权限")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public Result<Permission> deletePermission(@RequestParam("id") Long id) {
        permissionService.delete(id);
        return ResultUtil.success(id);
    }

}
