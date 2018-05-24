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
import com.fyerp.admin.domain.User;
import com.fyerp.admin.domain.dto.DepartmentDTO;
import com.fyerp.admin.domain.dto.UserDTO;
import com.fyerp.admin.service.DepartmentService;
import com.fyerp.admin.service.UserService;
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

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 查询用户列表（带分页）
     *
     * @return
     */
    @ApiOperation(value = "查询用户列表（带分页）", notes = "查询用户列表（带分页）")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object getUsers(@RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "size", required = false) Integer size,
                               @RequestParam(value = "sort_param", required = false, defaultValue = "createTime") String sortParam,
                               @RequestParam(value = "sort_desc|asc", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        logger.info("userList");
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return userService.findAll(sort);
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return userService.findAll(request).getContent();
        }
    }

//    /**
//     * 查询用户列表
//     *
//     * @return
//     */
//    @ApiOperation(value = "查询用户列表", notes = "查询用户列表")
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public Result<User> getUsers() {
//        logger.info("userList");
//        return ResultUtil.success(userService.findAll());
//    }

    /**
     * 查询单个用户
     *
     * @return
     */
    @ApiOperation(value = "查询单个用户", notes = "查询单个用户")
    @GetMapping(value = "/findOne/{id}")
    public User findOneUser(@PathVariable("id") Long id) {
        logger.info("findOneUser");
        return userService.findOne(id);
    }

    /**
     * 添加一个用户
     */
    @ApiOperation(value = "创建用户", notes = "根据user对象创建用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }

//    /**
//     * 添加多个用户
//     */
//    @ApiOperation(value = "创建用户", notes = "根据user对象创建用户")
//    @RequestMapping(value = "/adds", method = RequestMethod.POST)
//    public List<User> addUsers(@RequestBody List<User> users) {
//
//        return userService.save(users);
//    }

    /**
     * 更新一个用户
     *
     * @return
     */
    @ApiOperation(value = "更新用户", notes = "根据用户的id来更新用户信息")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user) {
        return userService.saveAndFlush(user);
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
     * 删除用户
     *
     * @param id
     */
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除用户")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam("id") Long id) throws Exception {
            userService.delete(id);
            System.out.println("删除了id为"+id+"的用户");
    }

}
