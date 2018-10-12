/*
 * 作者：xuda
 * 创建时间：18-4-19 下午5:14
 * 模块名称：admin
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.Task;
import com.fyerp.admin.domain.vo.TaskVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

public interface TaskService {

    Task findOne(Long taskId);

    Page<Task> findAll(Pageable pageable);

    List<Task> findAll();
    List<Task> findAll(List<Long> taskIds);

    List<Task> findAll(Sort sort);

    Task save(Task task);
    List<Task> save(List<Task> tasks);

    void delete(Long taskId);

    List<Task> findByPlanStartDateAfterAndPlanEndDateBefore(Date planStartDate, Date planEndDate);
}
