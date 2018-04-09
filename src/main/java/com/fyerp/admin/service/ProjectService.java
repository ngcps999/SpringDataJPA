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
import org.springframework.data.domain.Pageable;

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
     * @param projectId
     * @return
     */
    Project findOne(Integer projectId);

    /**
     * 查询所有项目（带分页）
     *
     * @return
     */
    List<Project> findAll();

    /**
     * 根据项目状态查项目
     *
     * @param projectState
     * @return
     */
//    List<Project> findProjectByProject_state(Integer projectState);

    /**
     * 新增/更新项目
     *
     * @param project
     * @return
     */
    Project save(Project project);

    /**
     * 删除项目
     *
     * @param projectId
     */
    void delete(Integer projectId);
}
