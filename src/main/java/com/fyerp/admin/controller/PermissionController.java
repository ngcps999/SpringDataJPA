/*
 * 作者：xuda
 * 创建时间：18-4-28 上午11:10
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Permission;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.service.PermissionService;
import com.fyerp.admin.utils.BeanUtils;
import com.fyerp.admin.utils.Constants;
import com.fyerp.admin.utils.ResultUtil;
import com.fyerp.admin.utils.UpdateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/permission")
@Api(value = "PermissionController", description = "权限Api")
@Scope("prototype")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    private final static Logger logger = LoggerFactory.getLogger(PermissionController.class);

    /**
     * 查询权限列表
     *
     * @return
     */
    @ApiOperation(value = "查询权限列表", notes = "查询权限列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result getPermissions(@RequestParam(value = "page", required = false) Integer page,
                                           @RequestParam(value = "size", required = false) Integer size,
                                           @RequestParam(value = "sortBy", required = false, defaultValue = "createTime") String sortParam,
                                           @RequestParam(value = "order", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return ResultUtil.success(permissionService.findAll(sort));
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return ResultUtil.success(permissionService.findAll(request).getContent());
        }
    }

    /**
     * 查询单个权限
     *
     * @return
     */
    @ApiOperation(value = "查询单个权限", notes = "查询单个权限")
    @GetMapping(value = "/find")
    public Result findOnePermission(@RequestParam("id") Long id) {
        logger.info("findOneProject");
        return ResultUtil.success(permissionService.findOne(id));
    }

    /**
     * 添加权限
     */
    @ApiOperation(value = "创建权限", notes = "根据permission对象创建权限")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addPermission(@RequestBody Permission permission) {
        if (permission.getPermissionId()!=0) {
            Permission permission1 = permissionService.findOne(permission.getPermissionId());
            UpdateUtil.copyNullProperties(permission1, permission);
        }
        return ResultUtil.success(permissionService.save(permission));
    }

    /**
     * 更新权限
     *
     * @return
     */
    @ApiOperation(value = "更新权限", notes = "根据权限的id来更新权限信息")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result updatePermission(@RequestBody Permission permission) {
        return ResultUtil.success(permissionService.save(permission));
    }

    /**
     * 删除权限
     *
     * @param id
     */
    @ApiOperation(value = "删除权限", notes = "根据id删除权限")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result deletePermission(@RequestParam("id") Long id) {
        permissionService.delete(id);
        return ResultUtil.success(Constants.DELETE_SUCCESS);
    }

}
