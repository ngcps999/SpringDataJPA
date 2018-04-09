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

import com.fyerp.admin.domain.Project;
import com.fyerp.admin.respository.ProjectRespository;
import com.fyerp.admin.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Project findOne(Integer projectId) {
        return respository.findOne(projectId);
    }

    @Override
    public List<Project> findAll() {
        return respository.findAll();
    }

//    @Override
//    public List<Project> findProjectByProject_state(Integer projectState) {
//        return respository.findProjectByProject_state(projectState);
//    }

    @Override
    public Project save(Project project) {
        return respository.save(project);
    }

    @Override
    public void delete(Integer projectId) {
        respository.delete(projectId);
    }
}
