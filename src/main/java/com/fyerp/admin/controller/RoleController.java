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
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    private final static Logger logger = LoggerFactory.getLogger(RoleController.class);

    /**
     * 查询角色列表
     * @return
     */
    @ApiOperation(value = "查询角色列表", notes = "查询角色列表")
    @RequestMapping(value = "/list/",method = RequestMethod.GET)
    public Result<Role> getRoles(@PathVariable("page") Integer page,
                                 @PathVariable("size") Integer size) {
        logger.info("roleList");
        PageRequest request = new PageRequest(page-1,size);
        return ResultUtil.success(roleService.findAll(request));
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
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public void deleteRole(@PathVariable("id") Long id) {
        roleService.delete(id);
    }

}
