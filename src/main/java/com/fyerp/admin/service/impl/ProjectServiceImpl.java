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
import com.fyerp.admin.domain.User;
import com.fyerp.admin.respository.ProjectRespository;
import com.fyerp.admin.respository.UserRespository;
import com.fyerp.admin.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Autowired
    private UserRespository userRespository;

    @Override
    public Project findOne(Integer id) {
        return respository.findOne(id);
    }

    @Override
    public List<Project> findAll() {
        return respository.findAll();
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
    public List<Project> findByPlanStartDateBetween(String planStartDate1, String planStartDate2) {
        return respository.findByPlanStartDateBetween(planStartDate1,planStartDate2);
    }

    @Override
    public Project save(Project project) {

        return respository.save(project);
    }

    @Override
    public void delete(Integer id) {
        respository.delete(id);
    }


}
