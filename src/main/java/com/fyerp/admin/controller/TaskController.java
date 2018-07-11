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
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.exception.TaskException;
import com.fyerp.admin.service.PlanService;
import com.fyerp.admin.service.ProjectService;
import com.fyerp.admin.service.TaskService;
import com.fyerp.admin.utils.ResultUtil;
import com.fyerp.admin.utils.UpdateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/task")
@Api(value = "TaskController", description = "任务Api")
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
     *
     * @return
     */
    @ApiOperation(value = "查询任务列表", notes = "查询任务列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result getTasks(@RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "size", required = false) Integer size,
                           @RequestParam(value = "sortBy", required = false, defaultValue = "createTime") String sortParam,
                           @RequestParam(value = "order", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        logger.info("taskList");
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return ResultUtil.success(taskService.findAll(sort));
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return ResultUtil.success(taskService.findAll(request).getContent());
        }

    }

    /**
     * 查询单个任务
     *
     * @return
     */
    @ApiOperation(value = "查询单个任务", notes = "查询单个任务")
    @GetMapping(value = "/find")
    public Result findOneTask(@RequestParam("id") Long id) {
        logger.info("findOneTask");
        return ResultUtil.success(taskService.findOne(id));
    }

    /**
     * 创建/更新任务
     *
     * @return
     */
    @ApiOperation(value = "添加/更新任务", notes = "根据Task对象属性创建/更新任务")
    @RequestMapping(value = "/addProject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result addTask(@RequestParam("taskName") String taskName,
                          @RequestParam("ids") List<Integer> planIds) {
        Task task = new Task();
        task.setTaskName(taskName);
        List<Plan> plans = planService.findAll(planIds);
        Set<Plan> plans1 = new HashSet<>(plans);

        task.setPlans(plans1);
        return ResultUtil.success(taskService.save(task));
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
        return ResultUtil.success(taskService.findByPlanStartDateAfterAndPlanEndDateBefore(planStartDate, planEndDate));
    }

    /**
     * 更新用户
     *
     * @return
     */
    @ApiOperation(value = "更新用户及其关联的角色", notes = "根据用户的id来更新用户")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result updateTaskPlans(@RequestBody Task task) {
        if (task.getTaskId() != 0) {
            Task task1 = taskService.findOne(task.getTaskId());
            //获取project1里的taskIds
            Set<Integer> roleIds = new HashSet<>();
            for (Plan plan : task1.getPlans()) {
                Integer roleId = plan.getPlanId();
                roleIds.add(roleId);
            }
            Set<Plan> taskPlans = task1.getPlans();
            //根据taskIds查询task库里是否存在，如果不存在就绑定到project1里
            //判断project1里是否包含task,有就继续，没有就添加
            for (Plan plan : planService.findAll(roleIds)) {
                if (taskPlans.contains(plan)) {
                    continue;
                }
                taskPlans.add(plan);
            }

            for (Plan plan : task.getPlans()) {
                taskPlans.add(planService.save(plan));
            }

            task.setPlans(new HashSet<>(taskPlans));

            Task save = taskService.save(task);
            Set<Plan> plans = save.getPlans();
            Iterator<Plan> iterator = plans.iterator();
            while (iterator.hasNext()) {
                Plan plan = iterator.next();
                if (plan.getStrategy() == 2) //strategy属性等于2时即删除task
                    iterator.remove();
            }

            UpdateUtil.copyNullProperties(task1, save);
            return ResultUtil.success(save);
        }

        Result result = new Result("请传入Id");
        return result;
    }

    /**
     * 更新任务下的计划
     *
     * @return
     */
    @ApiOperation(value = "更新任务下的计划", notes = "更新任务下的计划")
    @PutMapping(value = "/updatePlans")
    public Result updatePlans(@RequestParam("id") Long taskId,
                              @RequestParam("ids") List<Integer> planIds) {

        Task task = taskService.findOne(taskId);
        List<Plan> plans = planService.findAll(planIds);
        Set<Plan> taskPlans = task.getPlans();
        for (Plan plan : plans) {
            if (taskPlans.contains(plan)) {
                continue;
            }
            taskPlans.add(plan);
            taskService.save(task);

        }
        return ResultUtil.success(task);


    }

    /**
     * 删除任务里的计划
     *
     * @return
     */
    @ApiOperation(value = "删除任务里的计划", notes = "删除任务里的计划")
    @PutMapping(value = "/deleteProjects")
    public Result deletePlans(@RequestParam("id") Long taskId,
                            @RequestParam("ids") List<Integer> planIds) {
        Task task = taskService.findOne(taskId);
        List<Plan> plans = planService.findAll(planIds);
        Set<Plan> taskPlans = task.getPlans();
        for (Plan plan : plans) {
            if (taskPlans.contains(plan)) {
                taskPlans.remove(plan);
            }
        }
        taskService.save(task);
        return ResultUtil.success(task);
    }

    /**
     * 删除任务
     */
    @ApiOperation(value = "删除任务", notes = "根据id删除任务")
    @DeleteMapping(value = "/delete")
    public Result deleteTask(@RequestParam("id") Long taskId) {
        Task task = taskService.findOne(taskId);
        Set<Plan> plans = task.getPlans();
        plans.removeAll(plans);
        taskService.delete(taskId);
        return ResultUtil.success();
    }
}
