/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：ProjectServiceImpl.java
 * 作者：xuda
 * 时间：18-4-4 上午10:34
 *
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.Plan;
import com.fyerp.admin.domain.Project;
import com.fyerp.admin.domain.Task;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.exception.ProjectException;
import com.fyerp.admin.respository.ProjectRespository;
import com.fyerp.admin.service.PlanService;
import com.fyerp.admin.service.ProjectService;
import com.fyerp.admin.service.TaskService;
import com.fyerp.admin.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 项目服务层
 * @Author: xuda
 * @Date: 2018/4/4
 * @Time: 上午10:34
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRespository respository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private PlanService planService;

//    @Cacheable(value="projectInfo")
    @Override
    public Project findOne(Integer id) {
        return respository.findOne(id);
    }

//    @CacheEvict(value="projectInfo",allEntries = true)
//    @Override
//    public void evict(Project project){
//        System.out.println("==============从缓存中移除project==============");
//    }

    @Override
    public Page<Project> findAll(Pageable pageable) {
        return respository.findAll(pageable);
    }

    @Override
    public List<Project> findAll() {
        return respository.findAll();
    }

    @Override
    public List<Project> findAll(List<Integer> projectIds) {
        return respository.findAll(projectIds);
    }

    @Override
    public List<Project> findAll(Sort sort) {
        return respository.findAll(sort);
    }

    @Override
    public List<Project> findProjectsByProjectState(Integer state) {
        List<Project> projectsByStatus = respository.findProjectsByProjectState(state);
        for (Project project : projectsByStatus) {
            System.out.println(project);
        }
        return projectsByStatus;
    }

    @Override
    public List<Project> findByPlanStartDateBetween(Date planStartDate1, Date planStartDate2) {
        return respository.findByPlanStartDateBetween(planStartDate1,planStartDate2);
    }

    @Override
    public List<Project> findByPlanStartDateAfterAndPlanEndDateBefore(Date planStartDate, Date planEndDate) {
        return respository.findByPlanStartDateAfterAndPlanEndDateBefore(planStartDate,planEndDate);
    }

    @Override
    public List<Project> findByRealStartDateAfterAndRealEndDateBefore(Date realStartDate, Date realEndDate) {
        return respository.findByRealStartDateAfterAndRealEndDateBefore(realStartDate,realEndDate);
    }

    @Override
    public Page<Project> findByPriority(Integer priority,Pageable pageable) {
        return respository.findByPriority(priority,pageable);
    }

    @Override
    public Project save(Project project) {
        try {
            if(project.getProjectId() != null && project.getProjectId().intValue() > 0){
                //更新

                /*先将整个project整理出来，再整体入库*/
                Project project1 = respository.findOne(project.getProjectId());
//                1)整理task
                Set<Task> project1Task = project1.getTasks();

                Iterator<Task> taskIterator = project1Task.iterator();
                if(project.getTasks() != null){
                    //迭代传入参数中的task.如果不同则放入要更新对象中
                    Set<Task> newTasks = new HashSet<>();
                    for(Task task : project.getTasks()){
                        //比较原先task与当前task的id，如果不同，插入
                        boolean isInsert = true;
                        while (taskIterator.hasNext()){
                            Task oldTask = taskIterator.next();
                            if(oldTask.getTaskId().intValue() == task.getTaskId().intValue()){
                                //如果是ID一样的，更新操作，把原来的task删除掉并新增一个处理过的进去
                                taskIterator.remove();
                                isInsert = false;
                                Task newTask = task;
                                //对修改的和原先的两个task处理合并成一个,需要合并子对象plan
                                if(newTask.getPlans() != null){
                                    Set<Plan> newPlans = new HashSet<>();
                                    for(Plan newPlan : newTask.getPlans()){
                                        Iterator<Plan> planIterator = oldTask.getPlans().iterator();
                                        boolean insertPlan = true;
                                        while (planIterator.hasNext()){
                                            if(newPlan.getPlanId().intValue() == planIterator.next().getPlanId().intValue()){
                                                insertPlan = false;
                                                //替换
                                                newPlans.add(newPlan);
                                                planIterator.remove();
                                                break;
                                            }
                                        }
                                        if(insertPlan){
                                            newPlans.add(newPlan);
                                        }
                                    }
                                    oldTask.getPlans().addAll(newPlans);
                                    newTask.setPlans(oldTask.getPlans());

                                }else{
                                    newTask.setPlans(oldTask.getPlans());
                                }
                                newTasks.add(newTask);
                                break;
                            }
                        }
                        if(isInsert){
                            if(task.getPlans() != null){
                                for(Plan plan : task.getPlans()){
                                    if(plan.getPlanId().intValue() != 0){
                                        //新增task时，task中不允许带有已存在的plan
                                        throw new ProjectException(ResultEnum.PARAM_ERROR);
                                    }
                                }
                            }
                            newTasks.add(task);
                        }
                    }
                    project1Task.addAll(newTasks);
                }
                project.setTasks(project1Task);

                Project save = respository.save(project);

                //处理以删除元素不返回，strategy参数为2就是删除
                if(save.getStrategy().intValue() == 2){
                    return null;
                }else{
                    Set<Task> taskSet = new HashSet<>();
                    for(Task task : save.getTasks()){
                        if(task.getStrategy().intValue() != 2){
                            Set<Plan> planSet = new HashSet<>();
                            for(Plan plan : task.getPlans()){
                                if(plan.getStrategy() != 2){
                                    planSet.add(plan);
                                }
                            }
                            task.setPlans(planSet);
                            taskSet.add(task);
                        }
                    }
                    save.setTasks(taskSet);
                }
                return save;

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return respository.save(project);
    }

    @Override
    public Project delete(Integer id) {
        respository.delete(id);
        return null;
    }



}
