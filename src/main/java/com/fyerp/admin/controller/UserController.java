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

import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.service.UserService;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


/**
 * 用户管理API
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 查询用户列表
     * @return
     */
    @ApiOperation(value = "查询用户列表", notes = "查询用户列表")
    @RequestMapping(value = "/list/{page}/{size}",method = RequestMethod.GET)
    public Result<User> getUsers(@PathVariable("page") Integer page,
                                 @PathVariable("size") Integer size) {
        logger.info("userList");
        PageRequest request = new PageRequest(page-1,size);
        return ResultUtil.success(userService.findAll(request));
    }

    /**
     * 查询单个用户
     * @return
     */
    @ApiOperation(value = "查询单个用户", notes = "查询单个用户")
    @GetMapping(value = "/findOne/{id}")
    public Result<User> findOneUser(@PathVariable("id") Long id) {
        logger.info("findOneUser");
        return ResultUtil.success(userService.findOne(id));
    }
    /**
     * 添加用户
     */
    @ApiOperation(value = "创建用户", notes = "根据user对象创建用户")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result<User> addUser(@RequestBody User user) {

        return ResultUtil.success(userService.save(user));
    }

    /**
     * 更新一个用户
     * @return
     */
    @ApiOperation(value = "更新用户", notes = "根据用户的id来更新用户信息")
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<User> updateUser(@RequestBody User user) {

        return ResultUtil.success(userService.save(user));
    }

    /**
     * 删除用户
     * @param id
     */
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除用户")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result<User> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResultUtil.success(id);
    }
}
