/*
 * 作者：xuda
 * 创建时间：18-4-19 下午5:15
 * 模块名称：admin
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.Task;
import com.fyerp.admin.respository.BaseRespository.BaseRepository;

import java.util.Date;
import java.util.List;

public interface TaskRespository extends BaseRepository<Task,Long> {

    List<Task> findByTaskPlanStartDateAfterAndTaskPlanEndDateBefore(Date planStartDate, Date planEndDate);
}
