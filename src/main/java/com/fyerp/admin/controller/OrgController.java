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
import org.springframework.data.domain.PageRequest;
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
     * 查询组织架构列表
     * @return
     */
    @ApiOperation(value = "查询组织架构列表", notes = "查询组织架构列表")
    @RequestMapping(value = "/list/{page}/{size}",method = RequestMethod.GET)
    public Result<Org> getOrgs(@PathVariable("page") Integer page,
                               @PathVariable("size") Integer size) {
        logger.info("orgList");
        PageRequest request = new PageRequest(page-1,size);

        return ResultUtil.success(orgService.findAll(request));
    }

    /**
     * 添加组织
     * @param depName
     * @param sort
     * @param parentId
     * @param path
     * @return
     */
    @ApiOperation(value = "创建组织架构节点", notes = "根据Org对象创建组织架构节点")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result<Org> addOrg(@RequestParam("dep_name") String depName,
                              @RequestParam("sort") Integer sort,
                              @RequestParam("parent_id") Integer parentId,
                              @RequestParam("path") String path) {
        org.setDepName(depName);
        org.setSort(sort);
        org.setParentId(parentId);
        org.setPath(path);
        return ResultUtil.success(orgService.save(org));
    }

    /**
     * 更新组织架构
     *
     * @return
     */
    @ApiOperation(value = "更新组织架构", notes = "根据组织架构节点的id来更新组织架构")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "Integer", paramType = "path"),
//            @ApiImplicitParam(name = "project", value = "项目实体project", required = true, dataType = "Project")
//    })
    @PutMapping(value = "/update/{id}")
    public Result<Org> updateOrg(@PathVariable("id") Integer id,
                                 @RequestParam("dep_name") String depName,
                                 @RequestParam("sort") Integer sort,
                                 @RequestParam("parent_id") Integer parentId,
                                 @RequestParam("path") String path
                                     ) {
        org.setId(id);
        org.setDepName(depName);
        org.setSort(sort);
        org.setParentId(parentId);
        org.setPath(path);
        return ResultUtil.success(orgService.save(org));
    }

    /**
     * 删除岗位
     *
     * @param id
     */
    @ApiOperation(value = "删除岗位", notes = "根据id删除岗位")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public void deleteOrg(@PathVariable("id") Integer id) {
        orgService.delete(id);
    }


}
