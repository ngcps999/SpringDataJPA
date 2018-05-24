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

import com.fyerp.admin.domain.*;
import com.fyerp.admin.domain.vo.ProjectInfoVO;
import com.fyerp.admin.domain.vo.ProjectVO;
import com.fyerp.admin.service.ProjectCategoryService;
import com.fyerp.admin.service.ProjectService;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * 项目API层
 * @Author: xuda
 * @Date: 2018/4/3
 * @Time: 下午4:04
 */
@RestController
@RequestMapping(value = "/project")
@Api(value = "ProjectController",description = "项目Api")
@Scope("prototype")
public class ProjectController {

    private final static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectCategoryService categoryService;

    /**
     * 查询单个项目
     * @return
     */
    @ApiOperation(value = "查询单个项目", notes = "查询单个项目")
    @GetMapping(value = "/findOne/{id}")
    public Project findOneProject(@PathVariable("id") Integer id) {
        logger.info("findOneProject");
        if(id!= null) {
            return projectService.findOne(id);
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
    public Project findByPlanStartDateAfterAndPlanEndDateBefore(@PathVariable("planStartDate") Date planStartDate,
                                                                        @PathVariable("planEndDate") Date planEndDate) {
        return (Project) projectService.findByPlanStartDateAfterAndPlanEndDateBefore(planStartDate, planEndDate);
    }

    /**
     * 按实际开始时间和实际结束时间段查询
     *
     * @return
     */
    @ApiOperation(value = "按实际开始时间和实际结束时间段查询", notes = "按实际开始时间和实际结束时间段查询")
    @GetMapping(value = "/findByRealStartDateAfterAndRealEndDateBefore/{realStartDate}/{realEndDate}")
    public Project findByRealStartDateAfterAndRealEndDateBefore(@PathVariable("realStartDate") Date realStartDate,
                                                                        @PathVariable("realEndDate") Date realEndDate) {
        return (Project) projectService.findByPlanStartDateAfterAndPlanEndDateBefore(realStartDate, realEndDate);
    }
//
//    /**
//     * 按优先级从高到低查询项目列表（带分页）
//     *
//     * @return
//     */
//    @ApiOperation(value = "按优先级排序查询项目列表（带分页）", notes = "按优先级从高到低查询项目列表(第几页，每页几条)")
//    @RequestMapping(value = "/listOrderByPriority/{page}/{size}", method = RequestMethod.GET)
//    public Result<Project> getProjectsOrderByParam(@PathVariable("page") Integer page,
//                                                   @PathVariable("size") Integer size) {
//        logger.info("projectList");
//        Sort sort = new Sort(Sort.Direction.ASC, PRIORITY);
//        PageRequest request = new PageRequest(page - 1, size, sort);
//        return ResultUtil.success(projectService.findAll(request));
//    }
//
    /**
     * 查询项目列表（带分页）
     *
     * @return
     */
    @ApiOperation(value = "查询项目列表", notes = "查询项目列表(第几页，每页几条)")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object getProjects(@RequestParam(value = "page",required = false) Integer page,
                                     @RequestParam(value = "size",required = false) Integer size,
                                     @RequestParam(value = "sort_param", required = false, defaultValue = "createTime") String sortParam,
                                     @RequestParam(value = "sort_desc|asc", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) throws RuntimeException {
        logger.info("projectList");
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return projectService.findAll(sort);
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return projectService.findAll(request).getContent();
        }

    }

    /**
     * 查询项目列表（带分页）
     *
     * @return
     */
    @ApiOperation(value = "查询项目列表", notes = "查询项目列表(第几页，每页几条)")
//    @RequestMapping(value = "/list1", method = RequestMethod.GET)
    public Result getProjects1(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                       @RequestParam(value = "size",required = false,defaultValue = "10") Integer size) {
        logger.info("projectList");
        List<Project> projects = projectService.findAll();
        List<Integer> categoryTypeList = new ArrayList<>();
        for (Project project : projects) {
            categoryTypeList.add(project.getProjectCategory().getCategoryId());
        }

//        categoryService.findOne(categoryTypeList);

        Result result = new Result();
        ProjectVO projectVO = new ProjectVO();
        result.setData(Arrays.asList(projectVO));
        result.setCode(0);
        result.setMsg("成功了");
        return result;
    }

    /**
     * 按状态查询项目
     *
     * @return
     */
    @ApiOperation(value = "按状态查询项目,项目状态：0未进行，1正在进行，2遇到问题", notes = "按状态查询项目")
//    @ApiImplicitParam(name = "projectState", value = "项目状态", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/findProjectStatusList/{projectState}", method = RequestMethod.GET)
    public Project getProjectByStatus(@PathVariable("projectState") Integer projectState) {
        return (Project) projectService.findProjectsByProjectState(projectState);
    }

    @ApiOperation(value = "创建项目", notes = "根据Project对象创建项目")
    @PostMapping(value = "/add")
    public Project addProject(@RequestBody Project project) {
        return projectService.save(project);
    }

    @ApiOperation(value = "更新项目", notes = "根据项目的id来更新项目信息")
    @PutMapping(value = "/update")
    public Project updateProject(@RequestBody Project project) {
        return projectService.save(project);
    }

    /**
     * 删除项目
     * @param id
     */
    @ApiOperation(value = "删除项目", notes = "根据url的id来指定删除项目")
    @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteProject(@RequestParam("id") Integer id) {
        projectService.delete(id);
    }
}
