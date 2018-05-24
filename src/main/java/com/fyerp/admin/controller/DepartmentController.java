/*
 * 作者：xuda
 * 创建时间：18-5-8 上午11:13
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Department;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.dto.DepartmentDTO;
import com.fyerp.admin.service.DepartmentService;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/department")
@Api(value = "DepartmentController",description = "部门Api")
@Scope("prototype")
public class DepartmentController {

    private final static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询部门列表
     *
     * @return
     */
    @ApiOperation(value = "查询部门列表", notes = "查询部门列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object getDepartments(@RequestParam(value = "page",required = false) Integer page,
                                             @RequestParam(value = "size",required = false) Integer size,
                                             @RequestParam(value = "sort_param", required = false, defaultValue = "createTime") String sortParam,
                                             @RequestParam(value = "sort_desc|asc", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
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
    @GetMapping(value = "/findOne/{id}")
    public Department findOneDepartment(@PathVariable("id") Long departmentId) {
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
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "Integer", paramType = "path"),
//            @ApiImplicitParam(name = "project", value = "项目实体project", required = true, dataType = "Project")
//    })
    @PutMapping(value = "/update")
    public Department updateDepartment(@RequestBody Department department) {

        return departmentService.saveAndFlush(department);
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
