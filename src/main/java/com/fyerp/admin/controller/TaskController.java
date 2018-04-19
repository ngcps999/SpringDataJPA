/*
 * 作者：xuda
 * 创建时间：18-4-19 下午2:23
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.Task;
import com.fyerp.admin.service.TaskService;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    private final static Logger logger = LoggerFactory.getLogger(OrgController.class);

    @Autowired
    private TaskService taskService;

    private Task task = new Task();

    /**
     * 查询任务列表
     * @return
     */
    @ApiOperation(value = "查询任务列表", notes = "查询任务列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<Task> getTasks() {
        logger.info("taskList");
        return ResultUtil.success(taskService.findAll());
    }

    /**
     * 创建任务
     * @param taskDesc
     * @param taskEnddate
     * @param taskName
     * @param taskState
     * @return
     */
    @ApiOperation(value = "创建任务", notes = "根据Task对象创建任务")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<Task> addTask(@RequestParam("task_desc") String taskDesc,
                                @RequestParam("task_enddate") Date taskEnddate,
                                @RequestParam("task_name") String taskName,
                                @RequestParam("task_state") String taskState) {
        task.setTaskDesc(taskDesc);
        task.setTaskEnddate(taskEnddate);
        task.setTaskName(taskName);
        task.setTaskState(taskState);
        return ResultUtil.success(taskService.save(task));
    }

    /**
     * 更新任务
     * @return
     */
    @ApiOperation(value = "更新任务", notes = "根据任务的id来更新任务")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "Integer", paramType = "path"),
//            @ApiImplicitParam(name = "project", value = "项目实体project", required = true, dataType = "Project")
//    })
    @PutMapping(value = "/update/{id}")
    public Result<Task> updateOrg(@PathVariable("id") Integer id,
                                  @RequestParam("task_desc") String taskDesc,
                                  @RequestParam("task_enddate") Date taskEnddate,
                                  @RequestParam("task_name") String taskName,
                                  @RequestParam("task_state") String taskState) {
        task.setId(id);
        task.setTaskDesc(taskDesc);
        task.setTaskEnddate(taskEnddate);
        task.setTaskName(taskName);
        task.setTaskState(taskState);
        return ResultUtil.success(taskService.save(task));
    }

    /**
     * 删除任务
     * @param id
     */
    @ApiOperation(value = "删除任务", notes = "根据id删除任务")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable("id") Integer id) {
        taskService.delete(id);
    }
}
