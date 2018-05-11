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
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/task")
@CrossOrigin
public class TaskController {

    private final static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    /**
     * 查询任务列表
     * @return
     */
    @ApiOperation(value = "查询任务列表", notes = "查询任务列表")
    @RequestMapping(value = "/list/{page}/{size}", method = RequestMethod.GET)
    public Result<Task> getTasks(@PathVariable("page") Integer page,
                                 @PathVariable("size") Integer size) {
        logger.info("taskList");
        PageRequest request = new PageRequest(page-1,size);
        return ResultUtil.success(taskService.findAll(request));
    }
    /**
     * 查询单个任务
     * @return
     */
    @ApiOperation(value = "查询单个任务", notes = "查询单个任务")
    @GetMapping(value = "/findOne/{id}")
    public Result<Task> findOneTask(@PathVariable("id") Long id) {
        logger.info("findOneTask");
        return ResultUtil.success(taskService.findOne(id));
    }
    /**
     * 创建任务
     * @return
     */
    @ApiOperation(value = "创建任务", notes = "根据Task对象创建任务")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<Task> addTask(@RequestBody Task task) {
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
    @PutMapping(value = "/update")
    public Result<Task> updateTask(@RequestBody Task task) {

        return ResultUtil.success(taskService.save(task));
    }

    /**
     * 删除任务
     */
    @ApiOperation(value = "删除任务", notes = "根据id删除任务")
    @DeleteMapping(value = "/delete/{id}")
    public Result<Task> deleteTask(@PathVariable("id") Long taskId) {
        taskService.delete(taskId);
        return ResultUtil.success(taskId);
    }
}
