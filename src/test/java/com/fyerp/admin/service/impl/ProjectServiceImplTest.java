/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：ProjectServiceImplTest.java
 * 作者：xuda
 * 时间：18-4-4 上午10:40
 *
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.Project;
import com.fyerp.admin.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: xuda
 * @Date: 2018/4/4
 * @Time: 上午10:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProjectServiceImplTest {

    private Project project = new Project();

    @Autowired
    private ProjectService projectService;

    @Test
    public void findOne() {
        project = projectService.findOne(1);
        Assert.assertEquals(new Integer(1),project.getProjectId());
    }


    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,2);
        Page<Project> projects = projectService.findAll(request);
        for (Project project1 : projects) {
            System.out.println(project1.toString());

        }

//        List<Project> projects = projectService.findAll();
//        Assert.assertNotEquals(0,projects.size());

    }

//    @Test
//    public void save() {
//        project.setProjectName("xuda");
//        Project project1 = projectService.save(this.project);
//        Assert.assertNotNull(project1);
//
//    }

//    @Test
//    public void delete() {
//        projectService.delete(9);
//    }

    @Test
    public void findProjectsByProject_state() {
        projectService.findProjectsByProjectState(1);
    }


}