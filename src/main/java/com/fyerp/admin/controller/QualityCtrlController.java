package com.fyerp.admin.controller;

import com.fyerp.admin.domain.QualityControl;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.service.QualityControlService;
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
 * @Time:15:15
 **/
@RestController
@RequestMapping(value = "/qualityControl")
@Api(value = "QualityControl",description = "质检信息Api")
@Scope("prototype")
public class QualityCtrlController {

    private final static Logger logger = LoggerFactory.getLogger(QualityCtrlController.class);

    @Autowired
    private QualityControlService qualityControlService;


    /**
     * 查询质检列表
     * @return
     */
    @ApiOperation(value = "查询质检列表", notes = "查询质检列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result getContracts(@RequestParam(value = "page",required = false) Integer page,
                               @RequestParam(value = "size",required = false) Integer size,
                               @RequestParam(value = "sortBy", required = false, defaultValue = "createTime") String sortParam,
                               @RequestParam(value = "order", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        logger.info("ContractList");
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return ResultUtil.success(qualityControlService.findAll(sort));
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return ResultUtil.success(qualityControlService.findAll(request));
        }
    }

    /**
     * 查询单个质检报告
     * @return
     */
    @ApiOperation(value = "查询单个质检报告", notes = "查询单个质检报告")
    @GetMapping(value = "/find")
    public Result findOneQualityControl(@RequestParam("id") Integer qualityControlId) {
        logger.info("findOneDepartment");
        return ResultUtil.success(qualityControlService.findOne(qualityControlId));
    }

    /**
     * 更新质检信息
     * @return
     */
    @ApiOperation(value = "更新质检信息", notes = "更新质检信息")
    @PutMapping(value = "/update")
    public Result updateCustomer(@RequestBody QualityControl qualityControl) {
        return ResultUtil.success(qualityControlService.save(qualityControl));
    }


    /**
     * 删除质检信息
     * @param qualityControlId
     */
    @ApiOperation(value = "删除质检信息", notes = "根据url的id来指定删除质检信息")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public void deleteProject(@RequestParam("id") Integer qualityControlId) {
        qualityControlService.delete(qualityControlId);
    }

}
