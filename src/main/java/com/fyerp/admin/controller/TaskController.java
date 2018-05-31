/*
 * 作者：xuda
 * 创建时间：18-4-19 下午2:23
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Plan;
import com.fyerp.admin.domain.Project;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.Task;
import com.fyerp.admin.service.PlanService;
import com.fyerp.admin.service.ProjectService;
import com.fyerp.admin.service.TaskService;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/task")
@Api(value = "TaskController",description = "任务Api")
@Scope("prototype")
public class TaskController {

    private final static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private PlanService planService;

    /**
     * 查询任务列表
     * @return
     */
    @ApiOperation(value = "查询任务列表", notes = "查询任务列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object getTasks(@RequestParam(value = "page",required = false) Integer page,
                              @RequestParam(value = "size",required = false) Integer size,
                              @RequestParam(value = "sortBy", required = false, defaultValue = "createTime") String sortParam,
                              @RequestParam(value = "order", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        logger.info("taskList");
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return taskService.findAll(sort);
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return taskService.findAll(request).getContent();
        }
    }

    /**
     * 查询单个任务
     * @return
     */
    @ApiOperation(value = "查询单个任务", notes = "查询单个任务")
    @GetMapping(value = "/find")
    public Task findOneTask(@RequestParam("id") Long id) {
        logger.info("findOneTask");
        try {
            return taskService.findOne(id);
        }catch (Exception e){
            throw new RuntimeException("任务不存在");
        }
    }

    /**
     * 创建/更新任务
     *
     * @return
     */
    @ApiOperation(value = "添加/更新任务", notes = "根据Task对象属性创建/更新任务")
    @RequestMapping(value = "/addProject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Task addTask(@RequestParam("taskName") String taskName,
                        @RequestParam("ids") List<Integer> planIds) {
        Task task = new Task();
        task.setTaskName(taskName);
        List<Plan> plans = planService.findAll(planIds);
        Set<Plan> plans1 = new HashSet<>(plans);

        task.setPlans(plans1);
        return taskService.save(task);
    }

    /**
     * 更新任务下的计划
     *
     * @return
     */
    @ApiOperation(value = "更新任务下的计划", notes = "更新任务下的计划")
    @PutMapping(value = "/updatePlans")
    public Task updatePlans(@RequestParam("id") Long taskId,
                               @RequestParam("ids") List<Integer> planIds) {
        Task task = taskService.findOne(taskId);
        List<Plan> plans = planService.findAll(planIds);
        Set<Plan> taskPlans = task.getPlans();
        for (Plan plan : plans) {
            if (taskPlans.contains(plan)) {
                continue;
            }
            taskPlans.add(plan);
        }
        try {
            taskService.save(task);
        } catch (Exception e) {
            throw new RuntimeException("update fail!");
        }
        return task;
    }

    /**
     * 删除任务里的计划
     *
     * @return
     */
    @ApiOperation(value = "删除任务里的计划", notes = "删除任务里的计划")
    @PutMapping(value = "/deleteProjects")
    public Task deletePlans(@RequestParam("id") Long taskId,
                            @RequestParam("ids") List<Integer> planIds) {
        Task task = taskService.findOne(taskId);
        List<Plan> plans = planService.findAll(planIds);
        Set<Plan> taskPlans = task.getPlans();
        for (Plan plan : plans) {
            if (taskPlans.contains(plan)) {
                taskPlans.remove(plan);
            }
        }
        try {
            taskService.save(task);
        } catch (Exception e) {
            throw new RuntimeException("update fail!");
        }
        return task;
    }

    /**
     * 删除任务
     */
    @ApiOperation(value = "删除任务", notes = "根据id删除任务")
    @DeleteMapping(value = "/delete")
    public void deleteTask(@RequestParam("id") Long taskId) {
        taskService.delete(taskId);
    }
}
