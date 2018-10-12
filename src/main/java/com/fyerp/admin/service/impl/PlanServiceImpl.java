/*
 * 作者：xuda
 * 创建时间：18-4-20 下午4:35
 * 模块名称：admin
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.Plan;
import com.fyerp.admin.respository.PlanRespository;
import com.fyerp.admin.service.PlanService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRespository planRespository;

    @Override
    public Plan findOne(Integer planId) {
        return planRespository.findOne(planId);
    }

    @Override
    public Page<Plan> findAll(Pageable pageable) {
        return planRespository.findAll(pageable);
    }

    @Override
    public List<Plan> findAll(Set<Integer> planIds) {
        return planRespository.findAll(planIds);

    }

    @Override
    public List<Plan> findAll() {
        return planRespository.findAll();
    }

    @Override
    public List<Plan> findAll(List<Integer> planIds) {
        return planRespository.findAll(planIds);
    }


    @Override
    public List<Plan> findAll(Sort sort) {
        return planRespository.findAll(sort);
    }

    @Override
    public Plan save(Plan plan) {
        return planRespository.save(plan);
    }

    @Override
    public void delete(Integer planId) {
        planRespository.delete(planId);
    }

    @Override
    public List<Plan> findByPlanStartDateAfterAndPlanEndDateBefore(Date planStartDate, Date planEndDate) {
        return planRespository.findByPlanStartDateAfterAndPlanEndDateBefore(planStartDate,planEndDate);
    }
}
