/*
 * 作者：xuda
 * 创建时间：18-4-20 下午4:48
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Plan;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.Task;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.service.PlanService;
import com.fyerp.admin.utils.Constants;
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

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/plan")
@Api(value = "PlanController",description = "计划Api")
@Scope("prototype")
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
    public Result getPlans(@RequestParam(value = "page",required = false) Integer page,
                              @RequestParam(value = "size",required = false) Integer size,
                              @RequestParam(value = "sortBy", required = false, defaultValue = "createTime") String sortParam,
                              @RequestParam(value = "order", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        logger.info("planList");
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return ResultUtil.success(planService.findAll(sort));
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return ResultUtil.success(planService.findAll(request).getContent());
        }

    }

    /**
     * 查询单个计划
     *
     * @return
     */
    @ApiOperation(value = "查询单个计划", notes = "查询单个计划")
    @GetMapping(value = "/find")
    public Result findOnePlan(@RequestParam("id") Integer id) {
        logger.info("findOnePlan");
        return ResultUtil.success(planService.findOne(id));
    }

    /**
     * 按计划开始时间和计划结束时间段查询
     *
     * @return
     */
    @ApiOperation(value = "按计划开始时间和计划结束时间段查询", notes = "按计划开始时间和计划结束时间段查询")
    @GetMapping(value = "/findByPlanDate")
    public Result findByPlanStartDateAfterAndPlanEndDateBefore(@RequestParam("planStartDate") Date planStartDate,
                                                                   @RequestParam("planEndDate") Date planEndDate) {
        return ResultUtil.success(planService.findByPlanStartDateAfterAndPlanEndDateBefore(planStartDate, planEndDate));
    }

    /**
     * 创建计划
     * @param plan
     * @return
     */
    @ApiOperation(value = "创建计划", notes = "根据Plan对象创建计划")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addPlan(@RequestBody Plan plan
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
    @PutMapping(value = "/save")
    public Result savePlan(@RequestBody Plan plan) {
        return ResultUtil.success(planService.save(plan));

    }

    /**
     * 删除计划
     * @param planId
     */
    @ApiOperation(value = "删除计划", notes = "根据id删除计划")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result deleteTask(@RequestParam("id") Integer planId) {
        planService.delete(planId);
        return ResultUtil.success(Constants.DELETE_SUCCESS);
    }

}
