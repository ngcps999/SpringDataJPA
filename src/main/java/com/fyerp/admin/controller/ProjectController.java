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

import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @Author: xuda
 * @Date: 2018/4/3
 * @Time: 下午4:04
 */
@RestController(value = "/project")
public class ProjectController {

    private final static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;


    private Project project = new Project();

    /**
     * 查询项目列表
     * @return
     */
    @ApiOperation(value="查询项目列表", notes="查询项目列表")
    @GetMapping(value = "/list")
    public Result<Project> getProjects() {
        logger.info("projectList");
        return ResultUtil.success(projectService.findAll());
    }

    /**
     * 添加项目
     * @param project
     * @param bindingResult
     * @return
     */
    @ApiOperation(value="创建项目", notes="根据Project对象创建项目")
    @ApiImplicitParam(name = "project", value = "项目实体project", required = true, dataType = "Project")
    @PostMapping(value = "/add")
    public Result<Project> addProject(@Valid Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return null;
        }
        project.setProject_name(project.getProject_name());
        project.setStartdate(project.getStartdate());
        project.setEnddate(project.getEnddate());
        project.setMember(project.getMember());
        project.setProject_state(project.getProject_state());
        project.setProject_desc(project.getProject_desc());
        return ResultUtil.success(projectService.save(project));
    }


    /**
     * 更新一个项目
     * @return
     */
    @ApiOperation(value="更新项目", notes="根据项目的id来更新项目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "Integer",paramType = "path"),
            @ApiImplicitParam(name = "project", value = "项目实体project", required = true, dataType = "Project")
    })
    @PutMapping(value = "/update/{id}")
    public Result<Project> updateProject(@PathVariable("id") Integer id,
                                 @RequestParam("project_name") String project_name,
                                 @RequestParam("startdate")Date startdate,
                                 @RequestParam("enddate") Date enddate,
                                 @RequestParam("member") String member,
                                 @RequestParam("project_state") Integer project_state,
                                 @RequestParam("project_desc") String project_desc){
        project.setProject_name(project_name);
        project.setStartdate(startdate);
        project.setEnddate(enddate);
        project.setMember(member);
        project.setProject_state(project_state);
        project.setProject_desc(project_desc);
        return ResultUtil.success(projectService.save(project));
    }

    /**
     * 删除项目
     * @param id
     */
    @ApiOperation(value="删除项目", notes="根据url的id来指定删除项目")
    @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "Integer", paramType = "path")
    @DeleteMapping(value = "/delete/{id}")
    public void deleteProject(@PathVariable("id") Integer id) {
        projectService.delete(id);
    }



}
