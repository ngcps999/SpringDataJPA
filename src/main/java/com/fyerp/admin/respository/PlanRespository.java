/*
 * 作者：xuda
 * 创建时间：18-4-20 下午4:32
 * 模块名称：admin
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.Plan;
import com.fyerp.admin.respository.BaseRespository.BaseRepository;

import java.util.Date;
import java.util.List;

public interface PlanRespository extends BaseRepository<Plan,Integer> {
    List<Plan> findByPlanStartDateAfterAndPlanEndDateBefore(Date planStartDate, Date planEndDate);
}
