/*
 * 作者：xuda
 * 创建时间：18-4-27 下午2:33
 * 模块名称：admin
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.service.ActivtyService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ActivtyServiceImpl implements ActivtyService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Override
    public void startProcess(Long userId, Long roleId) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("userId", userId);
        variables.put("roleId", roleId);
        runtimeService.startProcessInstanceByKey("joinProcess", variables);
    }

    @Override
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskCandidateUser(assignee).list();
    }

    @Override
    public void completeTasks(Boolean joinApproved, String taskId) {
        Map<String, Object> taskVariables = new HashMap<>();
        taskVariables.put("joinApproved", joinApproved);
        taskService.complete(taskId, taskVariables);
    }
}
