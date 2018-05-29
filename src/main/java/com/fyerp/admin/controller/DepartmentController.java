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
import com.fyerp.admin.service.DepartmentService;
import com.fyerp.admin.service.UserService;
import com.fyerp.admin.utils.BeanUtils;
import com.fyerp.admin.utils.ResultUtil;
import com.fyerp.admin.utils.UpdateUtil;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * 创建部门
     *
     * @return
     */
    @ApiOperation(value = "创建部门", notes = "根据Task对象创建部门")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.save(department);
    }

    /**
     * 更新部门
     *
     * @return
     */
    @ApiOperation(value = "更新部门", notes = "根据部门的id来更新部门")
    @PutMapping(value = "/update")
    public Department updateDepartment(@RequestBody Department department) {
        if (department.getDepartmentId() != 0) {
            Department source = departmentService.findOne(department.getDepartmentId());
            BeanUtils.copyNotNullProperties(source, department);
        }
        return departmentService.saveAndFlush(department);
    }

    /**
     * 添加部门的员工
     *
     * @return
     */
    @ApiOperation(value = "添加部门员工", notes = "根据部门的id来更新部门员工")
    @PutMapping(value = "/saveDepartmentUsers")
    public DepartmentDTO saveDepartmentUsers(@RequestBody Department department) throws SQLException {

        return departmentService.saveDTO(department);
    }

    /**
     * 删除部门
     */
    @ApiOperation(value = "删除部门", notes = "根据id删除部门")
    @DeleteMapping(value = "/delete")
    public void deleteDepartment(@RequestParam("id") Long departmentId) {
        departmentService.delete(departmentId);
    }
}
