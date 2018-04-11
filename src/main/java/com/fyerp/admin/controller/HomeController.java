/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：HomeController.java
 * 作者：xuda
 * 时间：18-4-11 下午1:53
 *
 */

package com.fyerp.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: xuda
 * @Date: 2018/4/11
 * @Time: 下午1:53
 */
@Controller
public class HomeController {

    @GetMapping({"/","/index"})
    public String index(){
        return"/index";
    }

    @GetMapping(value="/login")
    public String login(){
        return"login";
    }
}
