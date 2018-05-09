/*
 * 作者：xuda
 * 创建时间：18-5-8 上午11:13
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Department;
import com.fyerp.admin.domain.Org;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.service.DepartmentService;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    private final static Logger logger = LoggerFactory.getLogger(OrgController.class);

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询部门列表
     *
     * @return
     */
    @ApiOperation(value = "查询部门列表", notes = "查询部门列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<Department> getDepartments() {
        logger.info("departmentList");
        return ResultUtil.success(departmentService.findAll());
    }

    /**
     * 查询单个部门
     *
     * @return
     */
    @ApiOperation(value = "查询单个部门", notes = "查询单个部门")
    @GetMapping(value = "/findOne/{id}")
    public Result<Department> findOneDepartment(@PathVariable("id") Long departmentId) {
        logger.info("findOneDepartment");

        return ResultUtil.success(departmentService.findOne(departmentId));
    }

    /**
     * 创建部门
     *
     * @return
     */
    @ApiOperation(value = "创建部门", notes = "根据Task对象创建部门")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<Department> addDepartment(@RequestBody Department department) {
        return ResultUtil.success(departmentService.save(department));
    }

    /**
     * 更新部门
     *
     * @return
     */
    @ApiOperation(value = "更新部门", notes = "根据部门的id来更新部门")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "Integer", paramType = "path"),
//            @ApiImplicitParam(name = "project", value = "项目实体project", required = true, dataType = "Project")
//    })
    @PutMapping(value = "/update")
    public Result<Department> updateDepartment(@RequestBody Department department) {

        return ResultUtil.success(departmentService.save(department));
    }

    /**
     * 删除部门
     */
    @ApiOperation(value = "删除部门", notes = "根据id删除部门")
    @DeleteMapping(value = "/delete/{id}")
    public Result<Department> deleteDepartment(@PathVariable("id") Long departmentId) {
        departmentService.delete(departmentId);
        return ResultUtil.success(departmentId);
    }
}
