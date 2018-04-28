///*
// * 作者：xuda
// * 创建时间：18-4-27 下午2:14
// * 模块名称：admin
// */
//
//package com.fyerp.admin.service;
//
//
//import org.activiti.engine.task.Task;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Service
//@Transactional
//public interface ActivtyService {
//
//    //开始流程，传入申请者的id以及公司的id
//    public void startProcess(Long personId, Long compId);
//
//    //获得某个人的任务别表
//    public List<Task> getTasks(String assignee);
//
//    //完成任务
//    public void completeTasks(Boolean joinApproved, String taskId);
//
//}
