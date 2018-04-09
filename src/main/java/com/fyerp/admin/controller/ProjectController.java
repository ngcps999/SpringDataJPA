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
import java.util.List;

/**
 * @Author: xuda
 * @Date: 2018/4/3
 * @Time: 下午4:04
 */
@RestController()
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
    @GetMapping(value = "/list")
    public Result<Project> getProjects() {
        logger.info("projectList");
        return ResultUtil.success(projectService.findAll());
    }

    /**
     * 按状态查询项目
     *
     * @return
     */
    @ApiOperation(value = "按状态查询项目", notes = "按状态查询项目")
    @ApiImplicitParam(name = "projectState", value = "项目状态", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/findProjectStatusList/{projectState}")
    public Result<Project> getProjectByStatus(@PathVariable("projectState") Integer projectState) {
        List<Project> projectsByProjectState = projectService.findProjectsByProjectState(project.getProjectState());
        return ResultUtil.success(projectsByProjectState);
    }

    /**
     * 添加项目
     *
     * @param project
     * @param bindingResult
     * @return
     */
    @ApiOperation(value = "创建项目", notes = "根据Project对象创建项目")
    @ApiImplicitParam(name = "project", value = "项目实体project", required = true, dataType = "Project")
    @PostMapping(value = "/add")
    public Result<Project> addProject(@Valid Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        }
        project.setProjectName(project.getProjectName());
        project.setStartdate(project.getStartdate());
        project.setEnddate(project.getEnddate());
        project.setMember(project.getMember());
        project.setProjectState(project.getProjectState());
        project.setProjectDesc(project.getProjectDesc());
        return ResultUtil.success(projectService.save(project));
    }


    /**
     * 更新一个项目
     *
     * @return
     */
    @ApiOperation(value = "更新项目", notes = "根据项目的id来更新项目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "project", value = "项目实体project", required = true, dataType = "Project")
    })
    @PutMapping(value = "/update/{id}")
    public Result<Project> updateProject(@PathVariable("id") Integer id,
                                         @RequestParam("project_name") String projectName,
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
     * 删除项目
     *
     * @param id
     */
    @ApiOperation(value = "删除项目", notes = "根据url的id来指定删除项目")
    @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "Integer", paramType = "path")
    @DeleteMapping(value = "/delete/{id}")
    public void deleteProject(@PathVariable("id") Integer id) {
        projectService.delete(id);
    }


}
