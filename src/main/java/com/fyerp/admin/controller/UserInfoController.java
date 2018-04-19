///*
// * Copyright (c) 2018.
// * 项目名称：fyerp.
// * 模块名称：fyerp
// * 文件名称：UserInfoController.java
// * 作者：xuda
// * 时间：18-4-16 下午4:09
// *
// */
//
//package com.fyerp.admin.controller;
//
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/userInfo")
//public class UserInfoController {
//
//	/**
//	 * 用户查询.
//	 * @return
//	 */
//	@RequestMapping("/userList")
//	public String userInfo(){
//		return "userInfo";
//	}
//
//	/**
//	 * 用户添加;
//	 * @return
//	 */
//	@RequestMapping("/userAdd")
//	@RequiresPermissions("userInfo:add")//权限管理;
//	public String userInfoAdd(){
//		return "userInfoAdd";
//	}
//
//	/**
//	 * 用户删除;
//	 * @return
//	 */
//	@RequestMapping("/userDel")
//	@RequiresPermissions("userInfo:del")//权限管理;
//	public String userDel(){
//		return "userInfoDel";
//	}
//
//}
