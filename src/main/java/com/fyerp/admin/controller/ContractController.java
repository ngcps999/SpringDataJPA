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

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 合同API层
 */
@RestController
@RequestMapping(value = "/contract")
public class ContractController {

    private final static Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private ContractService contractService;

    private Contract contract = new Contract();

    /**
     * 查询合同列表
     * @return
     */
    @ApiOperation(value = "查询合同列表", notes = "查询合同列表")
    @RequestMapping(value = "/list/{page}/{size}",method = RequestMethod.GET)
    public Result<Contract> getContracts(@PathVariable("page") Integer page,
                                         @PathVariable("size") Integer size) {
        logger.info("ContractList");
        PageRequest request = new PageRequest(page-1,size);

        return ResultUtil.success(contractService.findAll(request));
    }

    /**
     * @param audit_time
     * @param contract_code
     * @param contract_name
     * @param contract_state
     * @param contract_tax
     * @param contract_tax_rate
     * @param contract_type
     * @param effect_time
     * @param end_time
     * @param is_change
     * @param is_destroy
     * @param lost_effect_time
     * @param note
     * @param total_money
     * @return
     */
    @ApiOperation(value = "创建合同", notes = "根据Contract对象创建合同")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result<Contract> addContract(@RequestParam("audit_time") Date audit_time,
                                        @RequestParam("contract_code") String contract_code,
                                        @RequestParam("contract_money") Double contract_money,
                                        @RequestParam("contract_name") String contract_name,
                                        @RequestParam("contract_state") String contract_state,
                                        @RequestParam("contract_tax") Double contract_tax,
                                        @RequestParam("contract_tax_rate") Double contract_tax_rate,
                                        @RequestParam("contract_type") String contract_type,
                                        @RequestParam("effect_time") Date effect_time,
                                        @RequestParam("end_time") Date end_time,
                                        @RequestParam("is_change") String is_change,
                                        @RequestParam("is_destroy") String is_destroy,
                                        @RequestParam("lost_effect_time") Date lost_effect_time,
                                        @RequestParam("note") String note,
                                        @RequestParam("total_money") String total_money
                                        ) {
        contract.setAuditTime(audit_time);
        contract.setContractCode(contract_code);
        contract.setContractMoney(contract_money);
        contract.setContractName(contract_name);
        contract.setContractState(contract_state);
        contract.setContractTax(contract_tax);
        contract.setContractTaxRate(contract_tax_rate);
        contract.setContractType(contract_type);
        contract.setEffectTime(effect_time);
        contract.setEndTime(end_time);
        contract.setIsChange(is_change);
        contract.setIsDestroy(is_destroy);
        contract.setLostEffectTime(lost_effect_time);
        contract.setNote(note);
        contract.setTotalMoney(total_money);
        return ResultUtil.success(contractService.save(contract));
    }

    /**
     * 更新一个合同
     * @return
     */
    @ApiOperation(value = "更新合同", notes = "根据合同的id来更新合同信息")
    @RequestMapping(value = "/update/{contract_id}",method = RequestMethod.PUT)
    public Result<Contract> updateProject(@PathVariable("contract_id") Integer contract_id,
                                          @RequestParam("audit_time") Date audit_time,
                                          @RequestParam("contract_code") String contract_code,
                                          @RequestParam("contract_money") Double contract_money,
                                          @RequestParam("contract_name") String contract_name,
                                          @RequestParam("contract_state") String contract_state,
                                          @RequestParam("contract_tax") Double contract_tax,
                                          @RequestParam("contract_tax_rate") Double contract_tax_rate,
                                          @RequestParam("contract_type") String contract_type,
                                          @RequestParam("effect_time") Date effect_time,
                                          @RequestParam("end_time") Date end_time,
                                          @RequestParam("is_change") String is_change,
                                          @RequestParam("is_destroy") String is_destroy,
                                          @RequestParam("lost_effect_time") Date lost_effect_time,
                                          @RequestParam("note") String note,
                                          @RequestParam("total_money") String total_money) {
        contract.setContractId(contract_id);
        contract.setAuditTime(audit_time);
        contract.setContractCode(contract_code);
        contract.setContractMoney(contract_money);
        contract.setContractName(contract_name);
        contract.setContractState(contract_state);
        contract.setContractTax(contract_tax);
        contract.setContractTaxRate(contract_tax_rate);
        contract.setContractType(contract_type);
        contract.setEffectTime(effect_time);
        contract.setEndTime(end_time);
        contract.setIsChange(is_change);
        contract.setIsDestroy(is_destroy);
        contract.setLostEffectTime(lost_effect_time);
        contract.setNote(note);
        contract.setTotalMoney(total_money);
        return ResultUtil.success(contractService.save(contract));
    }

    /**
     * 删除合同
     * @param contract_id
     */
    @ApiOperation(value = "删除合同", notes = "根据url的id来指定删除合同")
    @RequestMapping(value = "/delete/{contract_id}",method = RequestMethod.DELETE)
    public void deleteProject(@PathVariable("contract_id") Integer contract_id) {
        contractService.delete(contract_id);
    }

}
