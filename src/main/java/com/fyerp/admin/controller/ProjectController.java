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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fyerp.admin.domain.Project;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.Task;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.domain.vo.ProjectVO;
import com.fyerp.admin.service.ProjectService;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
 * @Author: xuda
 * @Date: 2018/4/3
 * @Time: 下午4:04
 */
@RestController
@RequestMapping(value = "/project")
@CrossOrigin
public class ProjectController {

    private final static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    /**
     * 查询单个项目
     * @return
     */
    @ApiOperation(value = "查询单个项目", notes = "查询单个项目")
    @GetMapping(value = "/findOne/{id}")
    public Result<Project> findOneProject(@PathVariable("id") Integer id) {
        logger.info("findOneProject");
        if(id!= null) {
            return ResultUtil.success(projectService.findOne(id));
        } else {
            throw new RuntimeException("项目为空");
        }
    }

    /**
     * 按计划开始时间和计划结束时间段查询
     *
     * @return
     */
    @ApiOperation(value = "按计划开始时间和计划结束时间段查询", notes = "按计划开始时间和计划结束时间段查询")
    @GetMapping(value = "/findByPlanStartDateAfterAndPlanEndDateBefore/{planStartDate}/{planEndDate}")
    public Result<Project> findByPlanStartDateAfterAndPlanEndDateBefore(@PathVariable("planStartDate") Date planStartDate,
                                                                        @PathVariable("planEndDate") Date planEndDate) {
        return ResultUtil.success(projectService.findByPlanStartDateAfterAndPlanEndDateBefore(planStartDate, planEndDate));
    }

    /**
     * 按实际开始时间和实际结束时间段查询
     *
     * @return
     */
    @ApiOperation(value = "按实际开始时间和实际结束时间段查询", notes = "按实际开始时间和实际结束时间段查询")
    @GetMapping(value = "/findByRealStartDateAfterAndRealEndDateBefore/{realStartDate}/{realEndDate}")
    public Result<Project> findByRealStartDateAfterAndRealEndDateBefore(@PathVariable("realStartDate") Date realStartDate,
                                                                        @PathVariable("realEndDate") Date realEndDate) {
        return ResultUtil.success(projectService.findByPlanStartDateAfterAndPlanEndDateBefore(realStartDate, realEndDate));
    }

    /**
     * 按优先级从高到低查询项目列表（带分页）
     *
     * @return
     */
    @ApiOperation(value = "按优先级排序查询项目列表（带分页）", notes = "按优先级从高到低查询项目列表(第几页，每页几条)")
    @RequestMapping(value = "/listOrderByPriority/{page}/{size}", method = RequestMethod.GET)
    public Result<Project> getProjectsOrderByParam(@PathVariable("page") Integer page,
                                                   @PathVariable("size") Integer size) {
        logger.info("projectList");
        Sort sort = new Sort(Sort.Direction.ASC, "priority");
        PageRequest request = new PageRequest(page - 1, size, sort);
        return ResultUtil.success(projectService.findAll(request));
    }

    /**
     * 按创建时间从近到远查询项目列表（带分页）
     *
     * @return
     */
    @ApiOperation(value = "按创建时间从近到远查询项目列表（带分页）", notes = "按创建时间从近到远查询项目列表(第几页，每页几条)")
    @RequestMapping(value = "/listOrderByCreateTime/{page}/{size}", method = RequestMethod.GET)
    public Result<Project> getProjectsOrderByCreateTime(@PathVariable("page") Integer page,
                                                   @PathVariable("size") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest request = new PageRequest(page - 1, size, sort);
        return ResultUtil.success(projectService.findAll(request));
    }

    /**
     * 查询项目列表（带分页）
     *
     * @return
     */
    @ApiOperation(value = "查询项目列表", notes = "查询项目列表(第几页，每页几条)")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<Project> getProjects(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                       @RequestParam(value = "size",required = false,defaultValue = "10") Integer size) {
        logger.info("projectList");
        PageRequest request = new PageRequest(page - 1, size);
        return ResultUtil.success(projectService.findAll(request));
    }

    /**
     * 查询项目列表（带分页）
     *
     * @return
     */
    @ApiOperation(value = "查询项目列表", notes = "查询项目列表(第几页，每页几条)")
    @RequestMapping(value = "/list1", method = RequestMethod.GET)
    public Result<Project> getProjects1(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                       @RequestParam(value = "size",required = false,defaultValue = "10") Integer size) {
        logger.info("projectList");
        PageRequest request = new PageRequest(page - 1, size);
        return ResultUtil.success(projectService.findAll(request));
    }

    /**
     * 按状态查询项目
     *
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
    public Result<Project> addProject(@RequestBody Project project) {
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
    public Result<Project> deleteProject(@PathVariable("id") Integer id) {
        projectService.delete(id);
        return ResultUtil.success(id);
    }
}
