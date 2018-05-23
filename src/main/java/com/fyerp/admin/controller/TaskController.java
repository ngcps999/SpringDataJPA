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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/task")
@Api(value = "TaskController",description = "任务Api")
public class TaskController {

    private final static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    /**
     * 查询任务列表
     * @return
     */
    @ApiOperation(value = "查询任务列表", notes = "查询任务列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Task> getTasks(@RequestParam(value = "page",required = false) Integer page,
                              @RequestParam(value = "size",required = false) Integer size,
                              @RequestParam(value = "sort_param", required = false, defaultValue = "createTime") String sortParam,
                              @RequestParam(value = "sort_desc|asc", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        logger.info("taskList");
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return taskService.findAll(sort);
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return (List<Task>) taskService.findAll(request);
        }
    }

    /**
     * 查询单个任务
     * @return
     */
    @ApiOperation(value = "查询单个任务", notes = "查询单个任务")
    @GetMapping(value = "/findOne/{id}")
    public Task findOneTask(@PathVariable("id") Long id) {
        logger.info("findOneTask");
        return taskService.findOne(id);
    }

    /**
     * 创建任务
     * @return
     */
    @ApiOperation(value = "创建任务", notes = "根据Task对象创建任务")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Task addTask(@RequestBody Task task) {
        return taskService.save(task);
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
    public Task updateTask(@RequestBody Task task) {

        return taskService.save(task);
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
