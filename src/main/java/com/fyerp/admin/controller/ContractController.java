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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 合同API层
 */
@RestController
@RequestMapping(value = "/contract")
@Api(value = "ContractController",description = "合同Api")
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
    public List<Contract> getContracts(@RequestParam(value = "page",required = false) Integer page,
                                       @RequestParam(value = "size",required = false) Integer size,
                                       @RequestParam(value = "sort_param", required = false, defaultValue = "createTime") String sortParam,
                                       @RequestParam(value = "sort_desc|asc", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        logger.info("ContractList");
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return contractService.findAll(sort);
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return (List<Contract>) contractService.findAll(request);
        }
    }

    /**
     * 查询单个合同
     * @return
     */
    @ApiOperation(value = "查询单个合同", notes = "查询单个合同")
    @GetMapping(value = "/findOne/{id}")
    public Contract findOneContract(@PathVariable("id") Integer contractId) {
        logger.info("findOneDepartment");
        return contractService.findOne(contractId);
    }
    /**
     * 创建合同
     * @return
     */
    @ApiOperation(value = "创建合同", notes = "根据Contract对象创建合同")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Contract addContract(@RequestBody Contract contract) {
        return contractService.save(contract);
    }

    /**
     * 更新一个合同
     * @return
     */
    @ApiOperation(value = "更新合同", notes = "根据合同的id来更新合同信息")
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Contract updateProject(@RequestBody Contract contract) {
        return contractService.save(contract);
    }

    /**
     * 删除合同
     * @param contractId
     */
    @ApiOperation(value = "删除合同", notes = "根据url的id来指定删除合同")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public void deleteProject(@RequestParam("id") Integer contractId) {
        contractService.delete(contractId);
    }

}
