/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：ProjectService.java
 * 作者：xuda
 * 时间：18-4-4 上午10:19
 *
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

/**
 * 项目
 *
 * @Author: xuda
 * @Date: 2018/4/4
 * @Time: 上午10:19
 */
public interface ProjectService {

    /**
     * 查询单个项目
     *
     * @param id
     * @return
     */
    Project findOne(Integer id);

    /**
     * 查询所有项目（带分页）
     *
     * @return
     */
    Page<Project> findAll(Pageable pageable);

    /**
     * 查询所有项目（带分页）
     *
     * @return
     */
    List<Project> findAll();

    List<Project> findAll(Sort sort);

    /**
     * 根据项目状态查项目
     *
     * @param state
     * @return
     */
    List<Project> findProjectsByProjectState(Integer state);

    /**
     * 按计划进度查询
     *
     * @param planStartDate1
     * @param planStartDate2
     * @return
     */
    List<Project> findByPlanStartDateBetween(Date planStartDate1, Date planStartDate2);

    /**
     * 按计划开始时间和计划结束时间查询
     *
     * @param planStartDate
     * @param planEndDate
     * @return
     */
    List<Project> findByPlanStartDateAfterAndPlanEndDateBefore(Date planStartDate, Date planEndDate);

    /**
     * 按实际开始时间和实际结束时间查询
     *
     * @param realStartDate
     * @param realEndDate
     * @return
     */
    List<Project> findByRealStartDateAfterAndRealEndDateBefore(Date realStartDate, Date realEndDate);

    /**
     * 按优先级查询
     */
    Page<Project> findByPriority(Integer priority, Pageable pageable);


    /**
     * 新增
     *
     * @param project
     * @return
     */
    Project save(Project project);

    /**
     * 删除项目
     *
     * @param id
     */
    void delete(Integer id);
}
