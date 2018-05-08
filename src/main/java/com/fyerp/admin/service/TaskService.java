/*
 * 作者：xuda
 * 创建时间：18-4-19 下午5:14
 * 模块名称：admin
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    Page<Task> findAll(Pageable pageable);

    Task save(Task task);

    void delete(Long taskId);

}
