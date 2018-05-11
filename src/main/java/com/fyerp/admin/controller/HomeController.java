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

import com.fyerp.admin.domain.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

	@RequestMapping(value = "/msg",method = RequestMethod.GET)
	public String index(Model model){
		Msg msg =  new Msg("测试标题","测试内容","额外信息，只对管理员显示");
		model.addAttribute("msg", msg);
		return "index";
	}

	@GetMapping(value = "/f")
	public String file() {

	    return "file";
    }

    @GetMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui.html";
    }


}
