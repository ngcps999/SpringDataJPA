/*
 * 作者：xuda
 * 创建时间：18-5-8 上午11:13
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Department;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.domain.dto.DepartmentDTO;
import com.fyerp.admin.domain.dto.UserDTO;
import com.fyerp.admin.service.DepartmentService;
import com.fyerp.admin.service.UserService;
import com.fyerp.admin.utils.BeanUtils;
import com.fyerp.admin.utils.ResultUtil;
import com.fyerp.admin.utils.UpdateUtil;
import com.fyerp.admin.utils.convert.Department2DepartmentDTOConverter;
import io.swagger.annotations.Api;
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

    /**
     * 查询部门列表
     *
     * @return
     */
    @ApiOperation(value = "查询部门列表", notes = "查询部门列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object getDepartments(@RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "size", required = false) Integer size,
                                 @RequestParam(value = "sortBy", required = false, defaultValue = "createTime") String sortParam,
                                 @RequestParam(value = "order", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        logger.info("departmentList");
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return departmentService.findAll(sort);
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            Page<Department> departmentDTOS = departmentService.findAll(request);
            return departmentDTOS.getContent();
        }
    }

    /**
     * 查询单个部门
     *
     * @return
     */
    @ApiOperation(value = "查询单个部门", notes = "查询单个部门")
    @GetMapping(value = "/find")
    public Department findOneDepartment(@RequestParam("id") Long departmentId) {
        logger.info("findOneDepartment");

        return departmentService.findOne(departmentId);
    }
//
//    @PostMapping("/addDepTest")
//    public Department addDep(@RequestBody Department department) {
//
//        Department department1 = departmentService.findOne(department.getDepartmentId());
//        UpdateUtil.copyNullProperties(department1,department);
//
//        return departmentService.save(department);
//    }




    /**
     * 创建部门
     *
     * @return
     */
    @ApiOperation(value = "添加部门", notes = "根据department对象属性创建部门")
    @RequestMapping(value = "/addDep", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Department addDepartment(@RequestParam(value = "name", required = true) String depName, @RequestParam(value = "description", required = false) String depDesc, @RequestParam(value = "userIds", required = true) List<Long> userIds) {
        Department department = new Department();
        department.setDepName(depName);
        department.setDepDesc(depDesc);

        List<User> users = userService.findAll(userIds);
        Set<User> users1 = new HashSet<>(users);

        department.setUsers(users1);
        return departmentService.save(department);
    }

    /**
     * 更新部门
     *
     * @return
     */
//    @ApiOperation(value = "更新部门", notes = "根据部门的id来更新部门")
//    @PutMapping(value = "/update")
//    public Department updateDepartment(@RequestParam("id") Long departmentId) {
//        Department source = departmentService.findOne(departmentId);
//        Department department = new Department();
//        BeanUtils.copyNotNullProperties(source, department);
//        return departmentService.saveAndFlush(department);
//    }

    /**
     * 更新部门的员工
     *
     * @return
     */
    @ApiOperation(value = "更新部门的员工", notes = "根据部门的id来更新部门的员工")
    @PutMapping(value = "/updateUsers")
    public Department updateDepartmentUsers(@RequestParam(value = "departmentId", required = true) Long departmentId, @RequestParam(value = "userId", required = true) List<Long> userIds) {
        Department department = departmentService.findOne(departmentId);
        List<User> users = userService.findAll(userIds);
        Set<User> departmentUsers = department.getUsers();
        for (User user : users) {
            if (departmentUsers.contains(user)) {
                continue;
            }
            departmentUsers.add(user);
        }
        try {
            departmentService.save(department);
        } catch (Exception e) {
            throw new RuntimeException("update fail!");
        }
        return department;
    }

//    /**
//     * 更新部门的员工
//     *
//     * @return
//     */
//    @ApiOperation(value = "更新部门的员工", notes = "根据部门的id来更新部门的员工")
//    @PutMapping(value = "/updateUsersTest")
//    public Department updateDepartmentUsersTest(@RequestBody Department department) {
//        Department department1 = departmentService.findOne(department.getDepartmentId());
//        UpdateUtil.copyNullProperties(department1,department);
//        List<Long> userIds =new ArrayList<>();
//        for (User user : department.getUsers()) {
//            Long userId = user.getUserId();
//            userIds.add(userId);
//        }
//        List<User> users = userService.findAll(userIds);
//        Set<User> departmentUsers = department.getUsers();
//        for (User user : users) {
//            if (departmentUsers.contains(user)) {
//                continue;
//            }
//            departmentUsers.add(user);
//        }
//        try {
//            departmentService.save(department);
//        } catch (Exception e) {
//            throw new RuntimeException("update fail!");
//        }
//        return department;
//    }

    /**
     * 删除部门的员工
     *
     * @return
     */
    @ApiOperation(value = "删除部门员工", notes = "根据部门的id来添加部门员工")
    @PutMapping(value = "/deleteUsers")
    public Department deleteDepartmentUsers(@RequestParam(value = "departmentId", required = true) Long departmentId, @RequestParam(value = "userId", required = true) List<Long> userIds) {
        Department department = departmentService.findOne(departmentId);
        List<User> users = userService.findAll(userIds);
        Set<User> departmentUsers = department.getUsers();
        for (User user : users) {
            if (departmentUsers.contains(user)) {
                departmentUsers.remove(user);
            }
        }
        try {
            departmentService.save(department);
        } catch (Exception e) {
            throw new RuntimeException("update fail!");
        }
        return department;
    }

//    @ApiOperation(value = "更新部门员工", notes = "根据部门的id来更新部门员工")
//    @PutMapping(value = "/updateTest")
//    public Department updateDepartmentUsersTest(@RequestBody Department department) {
//        Department department1 = departmentService.findOne(department.getDepartmentId());
//        List<Long> userIds = new ArrayList<>();
//        Set<User> userList = department.getUsers();
//        for (User user : userList) {
//            if (user.getUserId() == 0) {
//                User user1 = userService.save(user);
//                userList.add(user1);
//            }
//            userIds.add(user.getUserId());
//        }
//
//        List<User> users = userService.findAll(userIds);
//        Set<User> departmentUsers = department1.getUsers();
//        for (User user : users) {
//            if (departmentUsers.contains(user)) {
//                continue;
//            }
//            departmentUsers.add(user);
//        }
//        try {
//            return departmentService.save(department1);
//        } catch (Exception e) {
//            throw new RuntimeException("update fail!");
//        }
//    }

    /**
     * 删除部门
     */
    @ApiOperation(value = "删除部门", notes = "根据id删除部门")
    @DeleteMapping(value = "/delete")
    public void deleteDepartment(@RequestParam("id") Long departmentId) {
        departmentService.delete(departmentId);
    }
}
