/*
 * 作者：xuda
 * 创建时间：18-5-8 上午11:13
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Department;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.Task;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.domain.dto.DepartmentDTO;
import com.fyerp.admin.domain.dto.UserDTO;
import com.fyerp.admin.domain.vo.DepartmentVO;
import com.fyerp.admin.domain.vo.TaskVO;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.exception.DepartmentException;
import com.fyerp.admin.service.DepartmentService;
import com.fyerp.admin.service.TaskService;
import com.fyerp.admin.service.UserService;
import com.fyerp.admin.utils.BeanUtils;
import com.fyerp.admin.utils.Constants;
import com.fyerp.admin.utils.ResultUtil;
import com.fyerp.admin.utils.UpdateUtil;
import com.fyerp.admin.utils.convert.Department2DepartmentDTOConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

import static com.fyerp.admin.utils.UpdateUtil.*;

@RestController
@RequestMapping(value = "/department")
@Api(value = "DepartmentController", description = "部门Api")
@Scope("prototype")
public class DepartmentController {

    private final static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    /**
     * 查询部门列表
     *
     * @return
     */
    @ApiOperation(value = "查询部门列表", notes = "查询部门列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result getDepartments(@RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "size", required = false) Integer size,
                                 @RequestParam(value = "sortBy", required = false, defaultValue = "createTime") String sortParam,
                                 @RequestParam(value = "order", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        logger.info("departmentList");
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return ResultUtil.success(departmentService.findAll(sort));
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            Page<Department> departmentDTOS = departmentService.findAll(request);
            return ResultUtil.success(departmentDTOS.getContent());
        }
    }

    /**
     * 查询单个部门
     *
     * @return
     */
    @ApiOperation(value = "查询单个部门", notes = "查询单个部门")
    @GetMapping(value = "/find")
    public Result findOneDepartment(@RequestParam("id") Long departmentId) {
        logger.info("findOneDepartment");
        return ResultUtil.success(departmentService.findOne(departmentId));
    }


    /**
     * 统计部门数量
     *
     * @param
     * @return
     */
    @ApiOperation(value = "统计部门数量", notes = "统计部门数量")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Result getCount() {
        List<Department> all = departmentService.findAll();
        return ResultUtil.success(all.size());
    }

    /**
     * 更新部门及员工
     *
     * @return
     */
    @ApiOperation(value = "更新部门", notes = "根据部门的id来更新部门")
    @PutMapping(value = "/update")
    public Result updateDepartment(@RequestBody Department department) {
        return ResultUtil.success(departmentService.updateDepartment(department));
    }


    /**
     * 给部门添加任务(strategy属性等于2时即删除task)
     *
     * @return
     */
    @ApiOperation(value = "给部门添加任务", notes = "根据部门的id来给部门添加任务")
    @PutMapping(value = "/addDepTask")
    public Result addDepartmentTask(@RequestBody Department department) {

        if (department.getDepartmentId() != 0) {
            Department department1 = departmentService.findOne(department.getDepartmentId());
            //获取project1里的taskIds
            List<Long> taskIds = new ArrayList<>();
            for (Task task : department1.getTasks()) {
                Long taskId = task.getTaskId();
                taskIds.add(taskId);
            }
            Set<Task> departmentTasks = department1.getTasks();
            //根据taskIds查询task库里是否存在，如果不存在就绑定到project1里
            //判断project1里是否包含task,有就继续，没有就添加
            for (Task task : taskService.findAll(taskIds)) {
                if (departmentTasks.contains(task)) {
                    continue;
                }
                departmentTasks.add(task);

            }

            for (Task task : department.getTasks()) {
                departmentTasks.add(taskService.save(task));
            }

            department.setTasks(new HashSet<>(departmentTasks));

            Department save = departmentService.save(department);
            Set<Task> tasks = save.getTasks();
            //strategy属性等于2时即删除user
            tasks.removeIf(task -> task.getStrategy() == 2);
            UpdateUtil.copyNullProperties(department1, save);
            return ResultUtil.success(save);
        }
        Result result = new Result("请传入Id");
        return result;
    }

    /**
     * 删除部门的员工
     *
     * @return
     */
    @ApiOperation(value = "删除部门员工")
    @PutMapping(value = "/deleteUsers")
    public Result deleteDepartmentUsers(@RequestParam(value = "departmentId", required = true) Long departmentId, @RequestParam(value = "userId", required = true) List<Long> userIds) {
        Department department = departmentService.findOne(departmentId);
        List<User> users = userService.findAll(userIds);
        Set<User> departmentUsers = department.getUsers();
        for (User user : users) {
            if (departmentUsers.contains(user)) {
                departmentUsers.remove(user);
            }
        }
        departmentService.save(department);
        return ResultUtil.success(department);
    }

    /**
     * 删除部门
     */
    @ApiOperation(value = "删除部门", notes = "根据id删除部门")
    @DeleteMapping(value = "/delete")
    public Result deleteDepartment(@RequestParam("id") Long departmentId) {
        Department department = departmentService.findOne(departmentId);
        Set<Task> tasks = department.getTasks();
        tasks.removeAll(tasks);
        Set<User> users = department.getUsers();
        users.removeAll(users);
        departmentService.delete(departmentId);
        return ResultUtil.success(Constants.DELETE_SUCCESS);
    }
}
