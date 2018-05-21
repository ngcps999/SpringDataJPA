///*
// * 作者：xuda
// * 创建时间：18-4-28 下午5:13
// * 模块名称：admin
// */
//
//package com.fyerp.admin.controller;
//
//import org.activiti.engine.RepositoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class MyController {
//
//    @Autowired
//    private RepositoryService repositoryService;
//
//    @RequestMapping("/welcome")
//    @ResponseBody
//    public String welcome() {
//
//        return "调用流程存储服务，查询部署数量："
//
//                + repositoryService.createDeploymentQuery().count();
//
//    }
//}
