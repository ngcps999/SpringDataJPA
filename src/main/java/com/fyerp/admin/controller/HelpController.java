package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Result;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.exception.OtherException;
import com.fyerp.admin.utils.GetJsonUtil;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:xiasc
 * @Date:2018/7/11
 * @Time:15:57
 **/
@RestController
@Api(value = "HelpController",description = "/")
public class HelpController {

    @GetMapping("/getMeta")
    public Result getMetaByName(String type){
        Object result = GetJsonUtil.getJsonByClass(type);
        if(result == null){
            throw new OtherException(ResultEnum.PARAM_ERROR);
        }
        return ResultUtil.success(result);
    }
}
