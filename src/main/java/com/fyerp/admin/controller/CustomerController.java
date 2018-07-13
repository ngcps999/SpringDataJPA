package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Customer;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.service.CustomerService;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * @author:xiasc
 * @Date:2018/7/13
 * @Time:13:35
 **/
@RestController
@RequestMapping(value = "/customer")
@Api(value = "CustomerController",description = "客户Api")
@Scope("prototype")
public class CustomerController {

    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;


    /**
     * 查询客户列表
     * @return
     */
    @ApiOperation(value = "查询客户列表", notes = "查询客户列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result getContracts(@RequestParam(value = "page",required = false) Integer page,
                               @RequestParam(value = "size",required = false) Integer size,
                               @RequestParam(value = "sortBy", required = false, defaultValue = "createTime") String sortParam,
                               @RequestParam(value = "order", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        logger.info("ContractList");
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return ResultUtil.success(customerService.findAll(sort));
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return ResultUtil.success(customerService.findAll(request));
        }
    }

    /**
     * 查询单个合同
     * @return
     */
    @ApiOperation(value = "查询单个客户", notes = "查询单个客户")
    @GetMapping(value = "/find")
    public Result findOneContract(@RequestParam("id") Integer contractId) {
        logger.info("findOneDepartment");
        return ResultUtil.success(customerService.findOne(contractId));
    }


    /**
     * 更新一个合同
     * @return
     */
    @ApiOperation(value = "更新客户", notes = "根据客户的id来更新客户信息")
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result updateCustomer(@RequestBody Customer customer) {
        return ResultUtil.success(customerService.save(customer));
    }

    /**
     * 删除合同
     * @param customerId
     */
    @ApiOperation(value = "删除客户", notes = "根据url的id来指定删除客户")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public void deleteProject(@RequestParam("id") Integer customerId) {
        customerService.delete(customerId);
    }

}
