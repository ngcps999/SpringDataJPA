/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：ProjectController.java
 * 作者：xuda
 * 时间：18-4-3 下午4:04
 *
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Project;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.Task;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.service.ProjectService;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 项目API层
 *
 * @Author: xuda
 * @Date: 2018/4/3
 * @Time: 下午4:04
 */
@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    private final static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    private Project project = new Project();

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 查询项目列表
     * @return
     */
    @ApiOperation(value = "查询单个项目", notes = "查询单个项目")
    @RequestMapping(value = "/findOne/{id}", method = RequestMethod.GET)
    public Result<Project> findOneProject(@PathVariable("id") Integer id) {
        logger.info("findOneProject");
        return ResultUtil.success(projectService.findOne(id));
    }

    /**
     * 查询项目列表
     *
     * @return
     */
    @ApiOperation(value = "查询项目列表", notes = "查询项目列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<Project> getProjects() {
        logger.info("projectList");
        return ResultUtil.success(projectService.findAll());
    }

    /**
     * 按状态查询项目
     * @return
     */
    @ApiOperation(value = "按状态查询项目,项目状态：0未进行，1正在进行，2遇到问题", notes = "按状态查询项目")
//    @ApiImplicitParam(name = "projectState", value = "项目状态", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/findProjectStatusList/{projectState}", method = RequestMethod.GET)
    public Result<Project> getProjectByStatus(@PathVariable("projectState") Integer projectState) {
        return ResultUtil.success(projectService.findProjectsByProjectState(projectState));
    }

    @ApiOperation(value = "创建项目", notes = "根据Project对象创建项目")
    @PostMapping(value = "/add")
    public Result<Project> addProject1(@RequestBody Project project) {

        return ResultUtil.success(projectService.save(project));
    }

    @ApiOperation(value = "更新项目", notes = "根据项目的id来更新项目信息")
    @PutMapping(value = "/update")
    public Result<Project> updateProject(@RequestBody Project project) {
        return ResultUtil.success(projectService.save(project));
    }

    /**
     * 删除项目
     * @param id
     */
    @ApiOperation(value = "删除项目", notes = "根据url的id来指定删除项目")
    @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteProject(@PathVariable("id") Integer id) {
        projectService.delete(id);
    }
}
