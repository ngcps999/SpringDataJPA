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
import com.fyerp.admin.domain.dto.ResultMsg;
import com.fyerp.admin.domain.vo.ProjectVO;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.exception.ProjectException;
import com.fyerp.admin.exception.UserException;
import com.fyerp.admin.service.*;
import com.fyerp.admin.utils.BeanUtils;
import com.fyerp.admin.utils.ResultUtil;
import com.fyerp.admin.utils.UpdateUtil;
import com.fyerp.admin.utils.search.SearchObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private PlanService planService;

    @Autowired
    private UserService userService;

    /**
     * 查询单个项目
     *
     * @return
     */
    @ApiOperation(value = "查询单个项目", notes = "查询单个项目")
    @GetMapping(value = "/find")
    public Result findOneProject(@RequestParam("id") Integer id) {
        logger.info("findOneProject");
        return ResultUtil.success(projectService.findOne(id));
    }

    /**
     * 按计划开始时间和计划结束时间段查询
     *
     * @return
     */
    @ApiOperation(value = "按计划开始时间和计划结束时间段查询", notes = "按计划开始时间和计划结束时间段查询")
    @GetMapping(value = "/findByPlanDate")
    public Result findByPlanStartDateAfterAndPlanEndDateBefore(@RequestParam("planStartDate") Date planStartDate,
                                                                @RequestParam("planEndDate") Date planEndDate) {
        return ResultUtil.success(projectService.
                findByPlanStartDateAfterAndPlanEndDateBefore(planStartDate, planEndDate));
    }

    /**
     * 按实际开始时间和实际结束时间段查询
     *
     * @return
     */
    @ApiOperation(value = "按实际开始时间和实际结束时间段查询", notes = "按实际开始时间和实际结束时间段查询")
    @GetMapping(value = "/findByRealDate")
    public Result findByRealStartDateAfterAndRealEndDateBefore(@RequestParam("realStartDate") Date realStartDate,
                                                                @RequestParam("realEndDate") Date realEndDate) {
        return ResultUtil.success(projectService.findByPlanStartDateAfterAndPlanEndDateBefore(realStartDate, realEndDate));
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
//    @PreAuthorize("hasAnyAuthority('project','all')")
    @ApiOperation(value = "查询项目列表", notes = "查询项目列表(第几页，每页几条)")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result getProjects(@RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "size", required = false) Integer size,
                              @RequestParam(value = "sort", required = false, defaultValue = "createTime") String sortParam,
                              @RequestParam(value = "order", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) throws RuntimeException {
        logger.info("projectList");
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return ResultUtil.success(projectService.findAll(sort));
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            List<Project> list = projectService.findAll(request).getContent();
            return ResultUtil.success(list);
        }

    }

    /**
     * 统计项目数量
     *
     * @param
     * @return
     */
    @ApiOperation(value = "统计项目数量", notes = "统计项目数量")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Result getCount() {
        List<Project> all = projectService.findAll();
        return ResultUtil.success(all.size());
    }

    /**
     * 按状态查询项目
     *
     * @return
     */
    @ApiOperation(value = "按状态查询项目,项目状态：0未进行，1正在进行，2遇到问题", notes = "按状态查询项目")
//    @ApiImplicitParam(name = "projectState", value = "项目状态", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/findProjectStatusList", method = RequestMethod.GET)
    public Result getProjectByStatus(@RequestParam("projectState") Integer projectState) {
        return ResultUtil.success(projectService.findProjectsByProjectState(projectState));
    }

    /**
     * 添加项目
     *
     * @return
     */
    @ApiOperation(value = "添加项目", notes = "根据Project对象创建项目")
    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public Result addProject(@RequestBody ProjectVO projectVO) {
        Project project = new Project();
        UpdateUtil.copyNullProperties(projectVO, project);
        Project project1 = projectService.save(project);
        BeanUtils.copyNotNullProperties(project1, projectVO);
        return ResultUtil.success(projectVO);
    }

    /**
     * 更新项目
     *
     * @return
     */
    @ApiOperation(value = "更新项目", notes = "更新项目")
//    @PutMapping(value = "/update")
    public Result updateProject(@RequestBody Project project) {
        //根据id来查询，再更新
        if (project.getProjectId() != 0) {
            Project project1 = projectService.findOne(project.getProjectId());
            //获取project1里的taskIds
            List<Long> taskIds = project1.getTasks().stream().map(Task::getTaskId).collect(Collectors.toList());
            Set<Task> project1Tasks = project1.getTasks();
            //根据taskIds查询task库里是否存在，如果不存在就绑定到project1里
            //判断project1里是否包含task,有就继续，没有就添加
            List<Task> taskList = taskService.findAll(taskIds);
            for (Task task : taskList) {
                if (project1Tasks.contains(task)) {
                    continue;
                }
                project1Tasks.add(task);
            }

            for (Task task : project1.getTasks()) {
                Task task1 = taskService.findOne(task.getTaskId());
                //获取task1里的planIds
                List<Integer> planIds = task1.getPlans().stream().map(Plan::getPlanId).collect(Collectors.toList());
                //根据planIds查询plan库里是否存在，如果不存在就绑定到task1里
                //判断task1里是否包含plan,有就继续，没有就添加
                Set<Plan> task1Plans = task1.getPlans();
                for (Plan plan : planService.findAll(planIds)) {
                    if (task1Plans.contains(plan)){
                        continue;
                    }
                    task1Plans.add(plan);
                }
            }

//----------------------
//id为0就新增
            for (Task task : project.getTasks()) {
                Task task1 = taskService.save(task);
                project1Tasks.add(task1);
                for (Plan plan : task.getPlans()) {
                    Task task3 = taskService.findOne(task1.getTaskId());
                    Set<Plan> task1Plans = task3.getPlans();
                    task1Plans.add(planService.save(plan));
                    task3.setPlans(task1Plans);
                }
            }

            project.setTasks(new HashSet<>(project1Tasks));

            Project save = projectService.save(project);

            //strategy参数为2就是删除
            Set<Task> tasks = save.getTasks();
            Iterator<Task> iterator = tasks.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (task.getStrategy() == 2) //strategy属性等于2时即删除task
                    iterator.remove();

            }
            for (Task task : tasks) {
                Iterator<Plan> iterator1 = task.getPlans().iterator();
                while (iterator1.hasNext()) {
                    Plan plan = iterator1.next();
                    if (plan.getStrategy() == 2)
                        iterator.remove();
                }
            }

//                UpdateUtil.copyNullProperties(project1, save);
            return ResultUtil.success(save);
        }
//        Result result = new Result("请传入Id");
        return ResultUtil.success(projectService.save(project));

    }

    /**
     * 删除项目
     *
     * @param projectId
     */

    @ApiOperation(value = "删除项目", notes = "删除项目前先确认项目下是否有任务")
//    @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result deleteProject(@RequestParam("id") Integer projectId) {
        return ResultUtil.success(projectService.delete(projectId));
    }



    @ApiOperation(value = "更新项目2", notes = "更新项目2")
    @PutMapping(value = "/save")
    public Result saveProject2(@RequestBody Project project){
        return  ResultUtil.success(projectService.save(project));
    }


    @ApiOperation(value = "根据当前登录用户获取项目",notes = "根据当前登录用户获取项目")
    @GetMapping(value = "getMyProject")
    public Result getMyProject(){
        SecurityUser user = (SecurityUser)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if(user == null || user.getUserId() == null || user.getUserId().longValue() == 0l){
            ResultUtil.error(ResultEnum.LOGIN_AGAIN);
        }
        return ResultUtil.success(projectService.findProjectByUserId(user.getUserId()));

    }


    @ApiOperation(value = "搜索",notes = "搜索")
    @PostMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public Result searchTest(@RequestBody SearchObj obj){
        List<Supplier> spl = new ArrayList<>();
        List<Project> list = projectService.searchTest(obj,obj.getPage(),obj.getAmount());
        return ResultUtil.success(list);
    }

}
