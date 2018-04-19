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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 项目API层
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

    /**
     * 查询项目列表
     *
     * @return
     */
    @ApiOperation(value = "查询项目列表", notes = "查询项目列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result<Project> getProjects() {
        logger.info("projectList");
        return ResultUtil.success(projectService.findAll());
    }

    /**
     * 按状态查询项目
     *
     * @return
     */
    @ApiOperation(value = "按状态查询项目,项目状态：0未进行，1正在进行，2遇到问题", notes = "按状态查询项目")
    @ApiImplicitParam(name = "projectState", value = "项目状态", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/findProjectStatusList/{projectState}",method = RequestMethod.GET)
    public Result<Project> getProjectByStatus(@PathVariable("projectState") Integer projectState) {

        return ResultUtil.success(projectService.findProjectsByProjectState(projectState));
    }

    /**
     * 添加项目
     * @param projectName
     * @param startdate
     * @param enddate
     * @param member
     * @param projectState
     * @param projectDesc
     * @return
     */
    @ApiOperation(value = "创建项目", notes = "根据Project对象创建项目")
//    @ApiImplicitParam(name = "project", value = "项目实体project", required = true, dataType = "path")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result<Project> addProject(@RequestParam("project_name") String projectName,
                                      @RequestParam("startdate") Date startdate,
                                      @RequestParam("enddate") Date enddate,
                                      @RequestParam("member") String member,
                                      @RequestParam("project_state") Integer projectState,
                                      @RequestParam("project_desc") String projectDesc) {
        project.setProjectName(projectName);
        project.setStartdate(startdate);
        project.setEnddate(enddate);
        project.setMember(member);
        project.setProjectState(projectState);
        project.setProjectDesc(projectDesc);
        return ResultUtil.success(projectService.save(project));
    }

    /**
     * 更新项目
     * @param id
     * @param projectName
     * @param startdate
     * @param enddate
     * @param member
     * @param projectState
     * @param projectDesc
     * @return
     */
    @ApiOperation(value = "更新项目", notes = "根据项目的id来更新项目信息")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public Result<Project> updateProject(@PathVariable("id") Integer id,
                                         @RequestParam("project_name") String projectName,
                                         @RequestParam("startdate") Date startdate,
                                         @RequestParam("enddate") Date enddate,
                                         @RequestParam("member") String member,
                                         @RequestParam("project_state") Integer projectState,
                                         @RequestParam("project_desc") String projectDesc) {
        project.setProjectId(id);
        project.setProjectName(projectName);
        project.setStartdate(startdate);
        project.setEnddate(enddate);
        project.setMember(member);
        project.setProjectState(projectState);
        project.setProjectDesc(projectDesc);
        return ResultUtil.success(projectService.save(project));
    }

    /**
     * 删除项目
     * @param id
     */
    @ApiOperation(value = "删除项目", notes = "根据url的id来指定删除项目")
    @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public void deleteProject(@PathVariable("id") Integer id) {
        projectService.delete(id);
    }


}
