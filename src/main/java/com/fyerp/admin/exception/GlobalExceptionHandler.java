/*
 * 作者：xuda
 * 创建时间：18-5-15 下午5:06
 * 模块名称：admin
 */

package com.fyerp.admin.exception;

import com.fyerp.admin.domain.Result;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局错误处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = DepartmentException.class)
    @ResponseBody
    public Result departmentExceptionHandler(DepartmentException e){
        e.printStackTrace();
        return ResultUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = TaskException.class)
    @ResponseBody
    public Result taskExceptionHandler(TaskException e){
        e.printStackTrace();
        return ResultUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = UserException.class)
    @ResponseBody
    public Result userExceptionHandler(UserException e){
        e.printStackTrace();
        return ResultUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = RoleException.class)
    @ResponseBody
    public Result roleExceptionHandler(RoleException e){
        e.printStackTrace();
        return ResultUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = ProjectException.class)
    @ResponseBody
    public Result projectExceptionHandler(ProjectException e){
        e.printStackTrace();
        return ResultUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = ProjectCategoryException.class)
    @ResponseBody
    public Result projectCategoryExceptionHandler(ProjectCategoryException e){
        e.printStackTrace();
        return ResultUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = OtherException.class)
    @ResponseBody
    public Result projectCategoryExceptionHandler(OtherException e){
        e.printStackTrace();
        return ResultUtil.error(e.getCode(),e.getMessage());
    }


    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Result runtimeExceptionHandler(RuntimeException e){
        e.printStackTrace();
        return ResultUtil.error(ResultEnum.RESULT_FAILED);
    }
//
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public void jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        String url = req.getRequestURI();
//        logger.error("request error at " + url, e);
//    }
}
