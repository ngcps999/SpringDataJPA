/*
 * 作者：xuda
 * 创建时间：18-4-28 上午11:04
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.Role;
import com.fyerp.admin.service.RoleService;
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
@RequestMapping(value = "/role")
@Api(value = "RoleController",description = "角色Api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    private final static Logger logger = LoggerFactory.getLogger(RoleController.class);

    /**
     * 查询角色列表
     * @return
     */
    @ApiOperation(value = "查询角色列表", notes = "查询角色列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result<Role> getRoles(@RequestParam(value = "page",required = false) Integer page,
                                 @RequestParam(value = "size",required = false) Integer size,
                                 @RequestParam(value = "sort_param", required = false, defaultValue = "createTime") String sortParam,
                                 @RequestParam(value = "sort_desc|asc", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        logger.info("roleList");
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return ResultUtil.success(roleService.findAll(sort));
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return ResultUtil.success(roleService.findAll(request));
        }
    }

    /**
     * 查询单个角色
     *
     * @return
     */
    @ApiOperation(value = "查询单个角色", notes = "查询单个角色")
    @GetMapping(value = "/findOne/{id}")
    public Result<Role> findOneRole(@PathVariable("id") Long id) {
        logger.info("findOneProject");
        return ResultUtil.success(roleService.findOne(id));
    }
    /**
     * 添加角色
     */
    @ApiOperation(value = "创建角色", notes = "根据user对象创建角色")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result<Role> addRole(@RequestBody Role role) {

        return ResultUtil.success(roleService.save(role));
    }

    /**
     * 更新一个角色
     * @return
     */
    @ApiOperation(value = "更新角色", notes = "根据角色的id来更新角色信息")
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<Role> updateRole(@RequestBody Role role) {

        return ResultUtil.success(roleService.save(role));
    }

    /**
     * 删除角色
     * @param id
     */
    @ApiOperation(value = "删除角色", notes = "根据id删除角色")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public Result<Role> deleteRole(@RequestParam("id") Long id) {
        roleService.delete(id);
        return ResultUtil.success(id);
    }

}
