/*
 * 作者：xuda
 * 创建时间：18-4-28 上午11:04
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Permission;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.Role;
import com.fyerp.admin.service.PermissionService;
import com.fyerp.admin.service.RoleService;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(value = "/role")
@Api(value = "RoleController", description = "角色Api")
@Scope("prototype")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    private final static Logger logger = LoggerFactory.getLogger(RoleController.class);

    /**
     * 查询角色列表
     *
     * @return
     */
    @ApiOperation(value = "查询角色列表", notes = "查询角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object getRoles(@RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "size", required = false) Integer size,
                           @RequestParam(value = "sortBy", required = false, defaultValue = "createTime") String sortParam,
                           @RequestParam(value = "order", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        logger.info("roleList");
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return roleService.findAll(sort);
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return roleService.findAll(request).getContent();
        }
    }

    /**
     * 查询单个角色
     *
     * @return
     */
    @ApiOperation(value = "查询单个角色", notes = "查询单个角色")
    @GetMapping(value = "/find")
    public Result<Role> findOneRole(@RequestParam("id") Long id) {
        logger.info("findOneProject");
        return ResultUtil.success(roleService.findOne(id));
    }

//    /**
//     * 添加角色
//     */
//    @ApiOperation(value = "创建角色", notes = "根据user对象创建角色")
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public Role addRole(@RequestBody Role role) {
//
//        return roleService.save(role);
//    }

    /**
     * 添加一个角色
     */
    @ApiOperation(value = "创建角色", notes = "根据role对象创建角色")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Role addRole(@RequestParam(value = "role", required = true) String role,
                        @RequestParam(value = "description", required = false) String description,
                        @RequestParam(value = "permissionIds",required = true) List<Long> permissionIds) {
        Role role1 = new Role();
        role1.setRole(role);
        role1.setDescription(description);

        List<Permission> permissions = permissionService.findAll(permissionIds);
        Set<Permission> permissions1 = new HashSet<>(permissions);
        role1.setPermissions(permissions1);
        return roleService.save(role1);
    }

//    /**
//     * 更新一个角色
//     *
//     * @return
//     */
//    @ApiOperation(value = "更新角色", notes = "根据角色的id来更新角色信息")
//    @RequestMapping(value = "/update", method = RequestMethod.PUT)
//    public Role updateRole(@RequestBody Role role) {
//
//        return roleService.save(role);
//    }
    /**
     * 更新角色
     *
     * @return
     */
    @ApiOperation(value = "更新角色关联的权限", notes = "根据角色的id来更新权限")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Role updateRolePermissions(@RequestParam(value = "roleId",required = true) Long roleId,
                                @RequestParam(value = "permissionIds",required = true) List<Long> permissionIds) {
        Role role = roleService.findOne(roleId);
        List<Permission> permissions = permissionService.findAll(permissionIds);
        Set<Permission> rolePermissions = role.getPermissions();
        for (Permission permission : permissions) {
            if (rolePermissions.contains(permission)) {
                continue;
            }
            rolePermissions.add(permission);
        }
        try {
            roleService.save(role);
        } catch (Exception e) {
            throw new RuntimeException("update fail!");
        }
        return role;
    }

    /**
     * 删除角色对应权限
     *
     * @return
     */
    @ApiOperation(value = "删除角色关联的权限", notes = "根据角色的id来删除权限")
    @RequestMapping(value = "/deletePermissions", method = RequestMethod.PUT)
    public Role deleteRolePermissions(@RequestParam(value = "roleId",required = true) Long roleId,
                                      @RequestParam(value = "permissionIds",required = true) List<Long> permissionIds) {
        Role role = roleService.findOne(roleId);
        List<Permission> permissions = permissionService.findAll(permissionIds);
        Set<Permission> rolePermissions = role.getPermissions();
        for (Permission permission : permissions) {
            if (rolePermissions.contains(permission)) {
                rolePermissions.remove(permission);
            }
        }
        try {
            roleService.save(role);
        } catch (Exception e) {
            throw new RuntimeException("delete fail!");
        }
        return role;
    }

    /**
     * 删除角色
     *
     * @param id
     */
    @ApiOperation(value = "删除角色", notes = "根据id删除角色")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteRole(@RequestParam("id") Long id) {
        roleService.delete(id);
    }

}
