/*
 * 作者：xuda
 * 创建时间：18-4-20 下午4:48
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Plan;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.Task;
import com.fyerp.admin.service.PlanService;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/plan")
@CrossOrigin
public class PlanController {

    private final static Logger logger = LoggerFactory.getLogger(PlanController.class);

    @Autowired
    private PlanService planService;

    /**
     * 查询计划列表
     * @return
     */
    @ApiOperation(value = "查询计划列表", notes = "查询计划列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<Plan> getPlans(@RequestParam(value = "page",required = false) Integer page,
                                 @RequestParam(value = "size",required = false) Integer size) {
        logger.info("planList");
        if (page == null && size == null) {
            return ResultUtil.success(planService.findAll());
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return ResultUtil.success(planService.findAll(request));
        }
    }

    /**
     * 查询单个计划
     *
     * @return
     */
    @ApiOperation(value = "查询单个计划", notes = "查询单个计划")
    @GetMapping(value = "/findOne/{id}")
    public Result<Plan> findOnePlan(@PathVariable("id") Integer id) {
        logger.info("findOnePlan");
        return ResultUtil.success(planService.findOne(id));
    }

    /**
     * 创建计划
     * @param plan
     * @return
     */
    @ApiOperation(value = "创建计划", notes = "根据Plan对象创建计划")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<Plan> addPlan(@RequestBody Plan plan
//            @RequestParam("plan_name") String planName,
//            @RequestParam("plan_content") String planContent
    ) {
//        plan.setPlanName(planName);
//        plan.setPlanContent(planContent);
        return ResultUtil.success(planService.save(plan));
    }

    /**
     * 更新计划
     * @return
     */
    @ApiOperation(value = "更新计划", notes = "根据计划的id来更新计划")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "Integer", paramType = "path"),
//            @ApiImplicitParam(name = "project", value = "项目实体project", required = true, dataType = "Project")
//    })
    @PutMapping(value = "/update")
    public Result<Plan> updatePlan(@RequestBody Plan plan) {
        return ResultUtil.success(planService.save(plan));
    }

    /**
     * 删除计划
     * @param planId
     */
    @ApiOperation(value = "删除计划", notes = "根据id删除计划")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<Task> deleteTask(@PathVariable("id") Integer planId) {
        planService.delete(planId);
        return ResultUtil.success(planId);
    }

}
