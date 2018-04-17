///*
// * Copyright (c) 2018.
// * 项目名称：fyerp.
// * 模块名称：fyerp
// * 文件名称：UserController.java
// * 作者：xuda
// * 时间：18-4-3 下午3:24
// *
// */
//
//package com.fyerp.admin.controller;
//
//import com.fyerp.admin.domain.JsonResult;
//import com.fyerp.admin.domain.Result;
//import com.fyerp.admin.domain.User;
//import com.fyerp.admin.service.UserService;
//import com.fyerp.admin.utils.ResultUtil;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import springfox.documentation.annotations.ApiIgnore;
//
//import javax.validation.Valid;
//import java.util.*;
//
///**
// * 用户管理API
// */
//@Controller
//@RequestMapping("user")
//@Scope("prototype")
//public class UserController {
//
//    private User user =new User();
//
//    @Autowired
//    private UserService userService;
////
//////     创建线程安全的Map
////    static Map<String, User> users = Collections.synchronizedMap(new HashMap<String, User>());
////
////    /**
////     * 根据ID查询用户
////     * @param id
////     * @return
////     */
////    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
////    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
////    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
////    public ResponseEntity<JsonResult> getUserById (@PathVariable(value = "id") Integer id){
////        JsonResult r = new JsonResult();
////        try {
////            User user = users.get(id);
////            r.setResult(user);
////            r.setStatus("ok");
////        } catch (Exception e) {
////            r.setResult(e.getClass().getName() + ":" + e.getMessage());
////            r.setStatus("error");
////            e.printStackTrace();
////        }
////        return ResponseEntity.ok(r);
////    }
////
////    /**
////     * 查询用户列表
////     * @return
////     */
////    @ApiOperation(value="获取用户列表", notes="获取用户列表")
////    @RequestMapping(value = "users", method = RequestMethod.GET)
////    public ResponseEntity<JsonResult> getUserList (){
////        JsonResult r = new JsonResult();
////        try {
////            List<User> userList = new ArrayList<User>(users.values());
////            r.setResult(userList);
////            r.setStatus("ok");
////        } catch (Exception e) {
////            r.setResult(e.getClass().getName() + ":" + e.getMessage());
////            r.setStatus("error");
////            e.printStackTrace();
////        }
////        return ResponseEntity.ok(r);
////    }
////
//////    public ResponseEntity<JsonResult> add (@RequestBody User user){
//////        JsonResult r = new JsonResult();
//////        try {
//////            users.put(String.valueOf(user.getUserId()), user);
//////            r.setResult(user.getUserId());
//////            r.setStatus("ok");
//////        } catch (Exception e) {
//////            r.setResult(e.getClass().getName() + ":" + e.getMessage());
//////            r.setStatus("error");
//////
//////            e.printStackTrace();
//////        }
//////        return ResponseEntity.ok(r);
//////    }
////
////    /**
////     * 根据id删除用户
////     * @param id
////     * @return
////     */
////    @ApiOperation(value="删除用户", notes="根据url的id来指定删除用户")
////    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
////    @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
////    public ResponseEntity<JsonResult> delete (@PathVariable(value = "id") Integer id){
////        JsonResult r = new JsonResult();
////        try {
////            users.remove(id);
////            r.setResult(id);
////            r.setStatus("ok");
////        } catch (Exception e) {
////            r.setResult(e.getClass().getName() + ":" + e.getMessage());
////            r.setStatus("error");
////
////            e.printStackTrace();
////        }
////        return ResponseEntity.ok(r);
////    }
////
////    /**
////     * 根据id修改用户信息
////     * @param user
////     * @return
////     */
////    @ApiOperation(value="更新信息", notes="根据url的id来指定更新用户信息")
////    @ApiImplicitParams({
////            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path"),
////            @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
////    })
////    @RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
////    public ResponseEntity<JsonResult> update (@PathVariable("id") String id, @RequestBody User user){
////        JsonResult r = new JsonResult();
////        try {
////            User u = users.get(id);
////            u.setUsername(user.getUsername());
////            users.put(id, u);
////            r.setResult(u);
////            r.setStatus("ok");
////        } catch (Exception e) {
////            r.setResult(e.getClass().getName() + ":" + e.getMessage());
////            r.setStatus("error");
////
////            e.printStackTrace();
////        }
////        return ResponseEntity.ok(r);
////    }
////
////    @ApiIgnore//使用该注解忽略这个API
////    @RequestMapping(value = "/hi", method = RequestMethod.GET)
////    public String  jsonTest() {
////        return " hi you!";
////    }
////
////    @ApiIgnore
////    @RequestMapping(value = "/getUser")
////    public User getUser(){
////        user.setUserId((long) 1);
////        user.setUsername("xuda");
////        user.setUsername("徐达");
////        return user;
////    }
//
//
//    /**
//     * 用户查询.
//     * @return
//     */
//    @RequestMapping("/userList")
//    @RequiresPermissions("user:view")//权限管理;
//    public String userInfo(){
//        return "user";
//    }
//
//    /**
//     * 用户添加;
//     * @return
//     */
//    @RequestMapping("/userAdd")
//    @RequiresPermissions("user:add")//权限管理;
//    public String userInfoAdd(){
//        return "userAdd";
//    }
//    /**
//     * 用户删除;
//     * @return
//     */
//    @RequestMapping("/userDel")
//    @RequiresPermissions("user:del")//权限管理;
//    public String userDel(){
//        return "userDel";
//    }
//
//}
