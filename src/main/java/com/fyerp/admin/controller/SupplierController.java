package com.fyerp.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fyerp.admin.domain.*;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.service.AircraftLeaseSupplierService;
import com.fyerp.admin.service.CameraSupplierService;
import com.fyerp.admin.service.OtherSupplierService;
import com.fyerp.admin.service.SupplierService;
import com.fyerp.admin.utils.ResultUtil;
import com.fyerp.admin.utils.SpringContextUtils;
import com.fyerp.admin.utils.SupplierServiceAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author:xiasc
 * @Date:2018/7/13
 * @Time:13:41
 **/
@RestController
@RequestMapping(value = "/supplier")
@Api(value = "SupplierController",description = "供应商Api")
@Scope("prototype")
public class SupplierController {

    private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);

//    @Autowired
//    private AircraftLeaseSupplierService airSupplierService;
//
//    @Autowired
//    private CameraSupplierService cameraSupplierService;
//
//    @Autowired
//    private OtherSupplierService otherSupplierService;

    /**
     * 查询供应商列表
     *
     * @return
     */
    @ApiOperation(value = "查询供应商列表", notes = "查询供应商列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result getContracts(@RequestParam(value = "type", required = true, defaultValue = "0") Integer type,
                               @RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "size", required = false) Integer size,
                               @RequestParam(value = "sortBy", required = false, defaultValue = "createTime") String sortParam,
                               @RequestParam(value = "order", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        logger.info("ContractList");
        if (type == null) {
            return ResultUtil.error(ResultEnum.PARAM_ERROR);
        }
        SupplierService service = SupplierServiceAdapter.getService(type);
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return ResultUtil.success(service.findAll(sort));
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return ResultUtil.success(service.findAll(request));
        }
    }


    /**
     * 查询单个供应商
     *
     * @return
     */
    @ApiOperation(value = "查询单个供应商", notes = "查询单个供应商")
    @GetMapping(value = "/find")
    public Result findOneSupplier(@RequestParam("id") Integer supplierId, @RequestParam("type") Integer type) {
        logger.info("findOneSupplier");
        if (type == null) {
            return ResultUtil.error(ResultEnum.PARAM_ERROR);
        }
        return ResultUtil.success(SupplierServiceAdapter.getService(type).findOne(supplierId));
    }


//    @ApiOperation(value = "更新供应商", notes = "根据id来更新供应商信息")
//    @RequestMapping(value = "/save", method = RequestMethod.PUT)
//    public Result saveSupplier(@RequestBody Map supplier) throws JsonProcessingException {
//        if(supplier.get("supplierType") != null && Integer.valueOf(supplier.get("supplierType").toString()) ==3){
//            ObjectMapper om = new ObjectMapper();
//            om.writeValueAsString(supplier);
//            new ObjectMapper().readValue(supplier,OtherSupplier.class);
//            return ResultUtil.success(SupplierServiceAdapter.getService(ot.getSupplierType()).save(ot));
//        }
//        return null;
////        return ResultUtil.success(SupplierServiceAdapter.getService(ot.getSupplierType()).save(supplier));
//    }


    @RequestMapping(value = "/saveOtherSupplier", method = RequestMethod.PUT)
    public Result saveOtherSupplier(@RequestBody OtherSupplier supplier){
        return ResultUtil.success(SupplierServiceAdapter.getService(supplier.getSupplierType()).save(supplier));
    }

    @RequestMapping(value = "/saveAircraftSupplier", method = RequestMethod.PUT)
    public Result saveAircraftSupplier(@RequestBody AircraftLeaseSupplier supplier){
        return ResultUtil.success(SupplierServiceAdapter.getService(supplier.getSupplierType()).save(supplier));
    }

    @RequestMapping(value = "/saveCameraSupplier", method = RequestMethod.PUT)
    public Result saveCameraSupplier(@RequestBody CameraSupplier supplier){
        return ResultUtil.success(SupplierServiceAdapter.getService(supplier.getSupplierType()).save(supplier));
    }
    /**
     * 删除供应商
     * @param supplierId
     */
    @ApiOperation(value = "删除供应商", notes = "根据url的id来指定删除供应商")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public void deleteProject(@RequestParam("id") Integer supplierId, @RequestParam("type") Integer type) {
        SupplierServiceAdapter.getService(type).delete(supplierId);
        ResultUtil.success();
    }


}