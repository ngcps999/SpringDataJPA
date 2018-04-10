/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：OrgController.java
 * 作者：xuda
 * 时间：18-4-9 下午4:26
 *
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Org;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.service.OrgService;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 组织架构API层
 * @Author: xuda
 * @Date: 2018/4/9
 * @Time: 下午4:26
 */
@RestController
@RequestMapping(value = "/org")
public class OrgController {
    private final static Logger logger = LoggerFactory.getLogger(OrgController.class);

    @Autowired
    private OrgService orgService;

    private Org org =new Org();

    /**
     * 查询项目列表
     * @return
     */
    @ApiOperation(value = "查询组织列表", notes = "查询组织列表")
    @GetMapping(value = "/list")
    public Result<Org> getOrgs() {
        logger.info("orgList");
        return ResultUtil.success(orgService.findAll());
    }

    /**
     * 添加项目
     * @param org
     * @param bindingResult
     * @return
     */
    @ApiOperation(value = "创建岗位", notes = "根据Org对象创建角色")
    @ApiImplicitParam(name = "org", value = "岗位实体org", required = true, dataType = "Project")
    @PostMapping(value = "/add")
    public Result<Org> addOrg(@Valid Org org, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        }
        org.setDepName(org.getDepName());
        org.setParentId(org.getParentId());
        org.setPath(org.getPath());
        org.setOrder(org.getOrder());
        return ResultUtil.success(orgService.save(org));
    }


//    /**
//     * 更新一个项目
//     *
//     * @return
//     */
//    @ApiOperation(value = "更新项目", notes = "根据项目的id来更新项目信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "Integer", paramType = "path"),
//            @ApiImplicitParam(name = "project", value = "项目实体project", required = true, dataType = "Project")
//    })
//    @PutMapping(value = "/update/{id}")
//    public Result<Org> updateProject(@PathVariable("id") Integer id) {
//        org.setProjectName(projectName);
//        project.setStartdate(startdate);
//        project.setEnddate(enddate);
//        project.setMember(member);
//        project.setProjectState(projectState);
//        project.setProjectDesc(projectDesc);
//        return ResultUtil.success(projectService.save(project));
//    }

    /**
     * 删除岗位
     *
     * @param id
     */
    @ApiOperation(value = "删除岗位", notes = "根据组织结构的id来指定删除岗位")
    @ApiImplicitParam(name = "id", value = "岗位ID", required = true, dataType = "Integer", paramType = "path")
    @DeleteMapping(value = "/delete/{id}")
    public void deleteOrg(@PathVariable("id") Integer id) {
        orgService.delete(id);
    }


}
