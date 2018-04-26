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

import com.fyerp.admin.domain.JsonResult;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.Role;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.service.UserService;
import com.fyerp.admin.utils.MD5Utils;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.*;

/**
 * 用户管理API
 */
@RestController
@RequestMapping("user")
public class UserController {

    private User user =new User();

    @Autowired
    private UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 查询用户列表
     * @return
     */
    @ApiOperation(value = "查询用户列表", notes = "查询用户列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result<User> getUsers() {
        logger.info("userList");
        return ResultUtil.success(userService.findAll());
    }

    /**
     * 添加用户
     * @param name
     * @param username
     * @param password
     * @param state
     * @return
     */
    @ApiOperation(value = "创建用户", notes = "根据user对象创建用户")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result<User> addUser(
            @RequestBody User user
                                ) {

        return ResultUtil.success(userService.save(user));
    }

    /**
     * 更新一个用户
     * @return
     */
    @ApiOperation(value = "更新用户", notes = "根据用户的id来更新用户信息")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public Result<User> updateUser(@PathVariable("id") Long id,
                                   @RequestParam("name") String name,
                                   @RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam("state") Integer state
                                   ) {
        user.setUserId(id);
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setState(state);
        return ResultUtil.success(userService.save(user));
    }

    /**
     * 删除用户
     * @param id
     */
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除用户")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
