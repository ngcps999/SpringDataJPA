/*
 * 作者：xuda
 * 创建时间：18-4-19 下午5:14
 * 模块名称：admin
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task save(Task task);

    void delete(Integer id);

}
