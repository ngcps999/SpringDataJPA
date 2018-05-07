/*
 * 作者：xuda
 * 创建时间：18-4-20 下午4:34
 * 模块名称：admin
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanService {

    Page<Plan> findAll(Pageable pageable);

    Plan save(Plan plan);

    void delete(Integer planId);
}