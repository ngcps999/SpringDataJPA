/*
 * 作者：xuda
 * 创建时间：18-4-19 下午5:25
 * 模块名称：admin
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.Task;
import com.fyerp.admin.respository.TaskRespository;
import com.fyerp.admin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRespository taskRespository;


    @Override
    public Task findOne(Long taskId) {
        return taskRespository.findOne(taskId);
    }

    @Override
    public Page<Task> findAll(Pageable pageable) {
        return taskRespository.findAll(pageable);
    }

    @Override
    public List<Task> findAll() {
        return taskRespository.findAll();
    }

    @Override
    public List<Task> findAll(List<Long> taskIds) {
        return taskRespository.findAll(taskIds);
    }

    @Override
    public List<Task> findAll(Sort sort) {
        return taskRespository.findAll(sort);
    }

    @Override
    public Task save(Task task) {
        return taskRespository.save(task);
    }

    @Override
    public void delete(Long taskId) {
        taskRespository.delete(taskId);
    }
}
