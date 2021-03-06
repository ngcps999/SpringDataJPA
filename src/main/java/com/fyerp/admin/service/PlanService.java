/*
 * 作者：xuda
 * 创建时间：18-4-20 下午4:34
 * 模块名称：admin
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PlanService {

    Plan findOne(Integer planId);

    Page<Plan> findAll(Pageable pageable);
    List<Plan> findAll(Set<Integer> planIds);

    List<Plan> findAll();
    List<Plan> findAll(List<Integer> planIds);
    List<Plan> findAll(Sort sort);

    Plan save(Plan plan);

    void delete(Integer planId);

    List<Plan> findByPlanStartDateAfterAndPlanEndDateBefore(Date planStartDate, Date planEndDate);
}
