/*
 * 作者：xuda
 * 创建时间：18-4-18 下午3:48
 * 模块名称：ContractController.java
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Contract;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.service.ContractService;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 合同API层
 */
@RestController
@RequestMapping(value = "/contract")
public class ContractController {

    private final static Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private ContractService contractService;

    /**
     * 查询合同列表
     * @return
     */
    @ApiOperation(value = "查询合同列表", notes = "查询合同列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result<Contract> getContracts(@RequestParam(value = "page",required = false) Integer page,
                                         @RequestParam(value = "size",required = false) Integer size) {
        logger.info("ContractList");
        if (page == null && size == null) {
            return ResultUtil.success(contractService.findAll());
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return ResultUtil.success(contractService.findAll(request));
        }
    }

    /**
     * 查询单个合同
     * @return
     */
    @ApiOperation(value = "查询单个合同", notes = "查询单个合同")
    @GetMapping(value = "/findOne/{id}")
    public Result<Contract> findOneContract(@PathVariable("id") Integer contractId) {
        logger.info("findOneDepartment");
        return ResultUtil.success(contractService.findOne(contractId));
    }
    /**
     * 创建合同
     * @return
     */
    @ApiOperation(value = "创建合同", notes = "根据Contract对象创建合同")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result<Contract> addContract(@RequestBody Contract contract) {
        return ResultUtil.success(contractService.save(contract));
    }

    /**
     * 更新一个合同
     * @return
     */
    @ApiOperation(value = "更新合同", notes = "根据合同的id来更新合同信息")
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<Contract> updateProject(@RequestBody Contract contract) {
        return ResultUtil.success(contractService.save(contract));
    }

    /**
     * 删除合同
     * @param contract_id
     */
    @ApiOperation(value = "删除合同", notes = "根据url的id来指定删除合同")
    @RequestMapping(value = "/delete/{contract_id}",method = RequestMethod.DELETE)
    public Result<Contract> deleteProject(@PathVariable("contract_id") Integer contract_id) {
        contractService.delete(contract_id);
        return ResultUtil.success(contract_id);
    }

}
