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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRespository taskRespository;

    @Override
    public List<Task> findAll() {
        return taskRespository.findAll();
    }

    @Override
    public Task save(Task task) {
        return taskRespository.save(task);
    }

    @Override
    public void delete(Integer id) {
        taskRespository.delete(id);
    }
}
