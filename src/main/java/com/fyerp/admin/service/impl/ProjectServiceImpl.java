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
import com.fyerp.admin.utils.search.SearchObj;
import com.fyerp.admin.domain.Task;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.exception.ProjectException;
import com.fyerp.admin.respository.ProjectRespository;
import com.fyerp.admin.service.PlanService;
import com.fyerp.admin.service.ProjectService;
import com.fyerp.admin.service.TaskService;
import com.fyerp.admin.service.UserService;
import com.fyerp.admin.utils.Constants;
import com.fyerp.admin.utils.search.*;
import org.activiti.engine.RuntimeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private UserService userService;

    @Autowired
    private EntityManager entityManager;

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
                if(project.getTasks() != null){
                    //迭代传入参数中的task.如果不同则放入要更新对象中
                    Set<Task> newTasks = new HashSet<>();
                    for(Task task : project.getTasks()){
                        //比较原先task与当前task的id，如果不同，插入
                        boolean isInsert = true;
                        Iterator<Task> taskIterator = project1Task.iterator();
                        while (taskIterator.hasNext()){
                            Task oldTask = taskIterator.next();
                            if(oldTask.getTaskId().intValue() == task.getTaskId().intValue()){
                                isInsert = false;
                                taskIterator.remove();
                                Task newTask = task;
                                //对修改的和原先的两个task处理合并成一个,需要合并子对象plan
                                if(newTask.getPlans() != null){
                                    Set<Plan> newPlans = new HashSet<>();
                                    for(Plan newPlan : newTask.getPlans()){
                                        Iterator<Plan> planIterator = oldTask.getPlans().iterator();
                                        boolean insertPlan = true;
                                        while (planIterator.hasNext()){
                                            Plan oldPlan = planIterator.next();

                                            if(newPlan.getPlanId().intValue() == oldPlan.getPlanId().intValue()){
                                                insertPlan = false;
                                                planIterator.remove();
                                                //替换
                                                newPlans.add(newPlan);

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
                            task.setCreateTime(new Date());
                            newTasks.add(task);
                        }
                    }
                    project1Task.addAll(newTasks);
                }
                project.setTasks(project1Task);

                Project save = respository.save(project);


                //处理以删除元素不返回，strategy参数为2就是删除
                if(save.getStrategy().intValue() == Constants.STRATEGY_DELETE){
                    return null;
                }else{
                    Set<Task> taskSet = new LinkedHashSet<>();
                    for(Task task : save.getTasks()){
                        if(task.getStrategy().intValue() != Constants.STRATEGY_DELETE){
                            Set<Plan> planSet = new LinkedHashSet<>();
                            for(Plan plan : task.getPlans()){
                                if(plan.getStrategy() != Constants.STRATEGY_DELETE){
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
        Project save = respository.save(project);
        return save;
    }

    @Override
    @Transactional
    public Project delete(Integer id) {
        Project project = respository.findOne(id);
        Set<Task> tasks = project.getTasks();
        for (Task task : tasks) {
            Set<Plan> taskPlans = task.getPlans();
            taskPlans.removeAll(taskPlans);
        }
        tasks.removeAll(tasks);
        respository.delete(id);
        return null;
    }

    @Override
    public Page<Project> findProjectBySearch(String column, String keyword, Pageable pageable) {
        return respository.findAll(new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if(StringUtils.isEmpty(column)){
                    return null;
                }
                Predicate predicate = cb.like(root.get(column).as(String.class),"%"+keyword+"%");
                query.where(predicate);
                return query.getRestriction();
            }
        },pageable);
    }

    @Override
    public List<Project> findProjectByUserId(Long userId) {
        List<Long> departmentIds = userService.findDepartmentByUser(userId);
        if(departmentIds == null || departmentIds.size() <= 0){
            return null;
        }
        List<Integer> projectIds = respository.findProjectIdsByDepartmentIdList(departmentIds);
        if(projectIds == null || projectIds.size() <= 0){
            return null;
        }
        List<Project> projects = respository.findAll(projectIds);
        return projects;
    }

    @Override
    public List searchTest(SearchObj obj, int page , int amount) {
        if(obj == null || obj.getFilters() == null || obj.getFilters().size() <= 0){
            return null;
        }
        Map map = SearchUtil.createSqlAndParam(obj.getFilters(),obj.getOrders(),page,amount,Project.class);
        String sql = " select project_id from project where   ";
        String sqlFilter = map.get("sql").toString();
        Query query = entityManager.createNativeQuery(sql+sqlFilter);
        if(map.get("params") != null){
            List list = (List)map.get("params");
            if(list != null && list.size() > 0){
                for(int i = 0; i < list.size(); i++){
                    query.setParameter(i+1,list.get(i));
                }
            }
        }
        List idList = query.getResultList();
        if(idList != null && idList.size() > 0){
            return respository.findAll(idList);
        }
        return null;
    }
}
