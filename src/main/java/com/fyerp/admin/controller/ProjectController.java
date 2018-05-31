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
import com.fyerp.admin.service.TaskService;
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

import static org.activiti.engine.impl.event.logger.handler.Fields.PRIORITY;

/**
 * 项目API层
 *
 * @Author: xuda
 * @Date: 2018/4/3
 * @Time: 下午4:04
 */
@RestController
@RequestMapping(value = "/project")
@Api(value = "ProjectController", description = "项目Api")
@Scope("prototype")
public class ProjectController {

    private final static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectCategoryService categoryService;

    @Autowired
    private TaskService taskService;

    /**
     * 查询单个项目
     *
     * @return
     */
    @ApiOperation(value = "查询单个项目", notes = "查询单个项目")
    @GetMapping(value = "/find")
    public Project findOneProject(@RequestParam("id") Integer id) {
        logger.info("findOneProject");
        try {
            return projectService.findOne(id);
        } catch (Exception e){
            throw new RuntimeException("项目不存在");
        }
    }

    /**
     * 按计划开始时间和计划结束时间段查询
     *
     * @return
     */
    @ApiOperation(value = "按计划开始时间和计划结束时间段查询", notes = "按计划开始时间和计划结束时间段查询")
    @GetMapping(value = "/findByPlanDate")
    public Project findByPlanStartDateAfterAndPlanEndDateBefore(@RequestParam("planStartDate") Date planStartDate,
                                                                @RequestParam("planEndDate") Date planEndDate) {
        return (Project) projectService.findByPlanStartDateAfterAndPlanEndDateBefore(planStartDate, planEndDate);
    }

    /**
     * 按实际开始时间和实际结束时间段查询
     *
     * @return
     */
    @ApiOperation(value = "按实际开始时间和实际结束时间段查询", notes = "按实际开始时间和实际结束时间段查询")
    @GetMapping(value = "/findByRealDate")
    public Project findByRealStartDateAfterAndRealEndDateBefore(@RequestParam("realStartDate") Date realStartDate,
                                                                @RequestParam("realEndDate") Date realEndDate) {
        return (Project) projectService.findByPlanStartDateAfterAndPlanEndDateBefore(realStartDate, realEndDate);
    }

    /**
     * 按优先级从高到低查询项目列表（带分页）
     *
     * @return
     */
    @ApiOperation(value = "按优先级排序查询项目列表（带分页）", notes = "按优先级从高到低查询项目列表(第几页，每页几条)")
    @RequestMapping(value = "/listOrderByPriority", method = RequestMethod.GET)
    public Result<Project> getProjectsOrderByParam(@RequestParam("page") Integer page,
                                                   @RequestParam("size") Integer size) {
        logger.info("projectList");
        Sort sort = new Sort(Sort.Direction.ASC, PRIORITY);
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
    public Object getProjects(@RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "size", required = false) Integer size,
                              @RequestParam(value = "sortBy", required = false, defaultValue = "createTime") String sortParam,
                              @RequestParam(value = "order", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) throws RuntimeException {
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

    /**
     * 创建/更新项目
     *
     * @return
     */
    @ApiOperation(value = "添加/更新项目", notes = "根据Project对象属性创建/更新项目")
    @RequestMapping(value = "/addProject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Project addProject(@RequestParam Project project1,
                              @RequestParam("ids") List<Long> taskIds) {
        Project project = new Project();
        project.setMap(project1.getMap());
        project.setFlyPlatform(project1.getFlyPlatform());

        List<Task> tasks = taskService.findAll(taskIds);
        Set<Task> tasks1 = new HashSet<>(tasks);

        project.setTasks(tasks1);
        return projectService.save(project);
    }

    /**
     * 更新项目下的任务
     *
     * @return
     */
    @ApiOperation(value = "更新项目类型下的任务", notes = "更新项目类型下的任务")
    @PutMapping(value = "/updateTasks")
    public Project updateTasks(@RequestParam("id") Integer projectId,
                              @RequestParam("ids") List<Long> taskIds) {
        Project project = projectService.findOne(projectId);
        List<Task> tasks = taskService.findAll(taskIds);
        Set<Task> projectTasks = project.getTasks();
        for (Task task : tasks) {
            if (projectTasks.contains(project)) {
                continue;
            }
            projectTasks.add(task);
        }
        try {
            projectService.save(project);
        } catch (Exception e) {
            throw new RuntimeException("update fail!");
        }
        return project;
    }

    /**
     * 删除项目下的任务
     *
     * @return
     */
    @ApiOperation(value = "删除项目下的任务", notes = "删除项目类型下的任务")
    @PutMapping(value = "/deleteProjects")
    public Project deleteTasks(@RequestParam("id") Integer projectId,
                               @RequestParam("ids") List<Long> taskIds) {
        Project project = projectService.findOne(projectId);
        List<Task> tasks = taskService.findAll(taskIds);
        Set<Task> projectTasks = project.getTasks();
        for (Task task : tasks) {
            if (projectTasks.contains(project)) {
                projectTasks.remove(task);
            }
        }
        try {
            projectService.save(project);
        } catch (Exception e) {
            throw new RuntimeException("update fail!");
        }
        return project;
    }

    /**
     * 删除项目
     *
     * @param id
     */
    @ApiOperation(value = "删除项目", notes = "删除项目前先确认项目下是否有任务")
    @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteProject(@RequestParam("id") Integer id) {
        try {
            projectService.delete(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("项目分类下有任务，请先删除任务。");
        }
    }
}
