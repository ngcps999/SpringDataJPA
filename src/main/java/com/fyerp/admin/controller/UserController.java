/*
 * Copyright (c) 2018.
 * 用户名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：UserController.java
 * 作者：xuda
 * 时间：18-4-3 下午3:24
 *
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Department;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.Role;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.domain.dto.UserDTO;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.exception.UserException;
import com.fyerp.admin.service.DepartmentService;
import com.fyerp.admin.service.RoleService;
import com.fyerp.admin.service.UserService;
import com.fyerp.admin.utils.BeanUtils;
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

import java.util.*;

/**
 * 用户管理API
 */
@RestController
@RequestMapping("user")
@Api(value = "UserController", description = "用户Api")
@Scope("prototype")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DepartmentService departmentService;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 查询用户列表（带分页）
     *
     * @return
     */
    @ApiOperation(value = "查询用户列表（带分页）", notes = "查询用户列表（带分页）")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result getUsers(@RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "size", required = false) Integer size,
                           @RequestParam(value = "sortBy", required = false, defaultValue = "createTime") String sortParam,
                           @RequestParam(value = "order", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        logger.info("userList");
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return ResultUtil.success(userService.findAll(sort));
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return ResultUtil.success(userService.findAll(request).getContent());
        }

    }



    /**
     * 查询单个用户
     *
     * @return
     */
    @ApiOperation(value = "查询单个用户", notes = "查询单个用户")
    @GetMapping(value = "/find")
    public Result findOneUser(@RequestParam("id") Long id) {
        logger.info("findOneUser");
        return ResultUtil.success(userService.findOne(id));
    }

    /**
     * 添加一个用户
     */
    @ApiOperation(value = "创建用户", notes = "根据user对象创建用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addUser(@RequestBody UserDTO userDTO)  {
        User user = new User();
        UpdateUtil.copyNullProperties(userDTO,user);
        User user1 = userService.save(user);
        BeanUtils.copyNotNullProperties(user1,userDTO);
        return ResultUtil.success(userDTO);
    }


    /**
     * 更新用户
     *
     * @return
     */
    @ApiOperation(value = "更新用户及其关联的角色", notes = "根据用户的id来更新用户")
    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    public Result saveUserRoles(@RequestBody User user){
        return ResultUtil.success(userService.saveUser(user));
    }

//    /**
//     * 更新多个用户
//     *
//     * @return
//     */
//    @ApiOperation(value = "更新多个用户", notes = "根据用户的id来更新用户信息")
//    @RequestMapping(value = "/updates", method = RequestMethod.PUT)
//    public List<User> updateUsers(@RequestBody List<User> users) {
//        return userService.save(users);
//    }
    /**
     * 删除用户关联的角色
     *
     * @return
     */
    @ApiOperation(value = "删除用户关联的角色", notes = "根据用户的id来删除对应角色")
    @PutMapping(value = "/deleteRoles")
    public Result deleteUserRoles(@RequestParam(value = "userId",required = true) Long userId) {
        User user = userService.findOne(userId);
        Set<Long> roleIds = new HashSet<>();
        for (Role role : user.getRoles()) {
            Long roleId = role.getRoleId();
            roleIds.add(roleId);
        }

        List<Role> roles = roleService.findAll(roleIds);
        Set<Role> userRoles = user.getRoles();
        for (Role role : roles) {
            if (userRoles.contains(role)) {
                userRoles.removeAll(roles);
            }
        }
        userService.save(user);
        return ResultUtil.success(user);

    }

    /**
     * 删除用户
     *
     * @param userId
     */
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除用户")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result deleteUser(@RequestParam("id") Long userId) throws Exception {
        User user = userService.findOne(userId);
        Set<Role> roles = user.getRoles();
        roles.removeAll(roles);
        userService.delete(userId);
        return ResultUtil.success();
    }

}
