/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：HomeController.java
 * 作者：xuda
 * 时间：18-4-16 下午2:45
 *
 */

package com.fyerp.admin.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class HomeController {

	@ApiOperation(value = "首页", notes = "首页")
	@RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
	public String index(){
		return "/index";
	}

	@ApiOperation(value = "登陆", notes = "登陆")
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
//
//	// 登录提交地址和applicationontext-shiro.xml配置的loginurl一致。 (配置文件方式的说法)
//		@RequestMapping(value="/login",method=RequestMethod.POST)
//		public String login(HttpServletRequest request, Map<String, Object> map) throws Exception {
//			System.out.println("HomeController.login()");
//			// 登录失败从request中获取shiro处理的异常信息。
//			// shiroLoginFailure:就是shiro异常类的全类名.
//			String exception = (String) request.getAttribute("shiroLoginFailure");
//
//			System.out.println("exception=" + exception);
//			String msg = "";
//			if (exception != null) {
//				if (UnknownAccountException.class.getName().equals(exception)) {
//					System.out.println("UnknownAccountException -- > 账号不存在：");
//					msg = "UnknownAccountException -- > 账号不存在：";
//				} else if (IncorrectCredentialsException.class.getName().equals(exception)) {
//					System.out.println("IncorrectCredentialsException -- > 密码不正确：");
//					msg = "IncorrectCredentialsException -- > 密码不正确：";
//				} else if ("kaptchaValidateFailed".equals(exception)) {
//					System.out.println("kaptchaValidateFailed -- > 验证码错误");
//					msg = "kaptchaValidateFailed -- > 验证码错误";
//				} else {
//					msg = "else >> "+exception;
//					System.out.println("else -- >" + exception);
//				}
//			}
//			map.put("msg", msg);
//			// 此方法不处理登录成功,由shiro进行处理.
//			return "/login";
//		}
//
}
