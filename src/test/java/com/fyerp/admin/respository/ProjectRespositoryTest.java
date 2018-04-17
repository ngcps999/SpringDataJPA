/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：ProjectRespositoryTest.java
 * 作者：xuda
 * 时间：18-4-3 下午4:36
 *
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.Project;
import io.swagger.models.auth.In;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: xuda
 * @Date: 2018/4/3
 * @Time: 下午4:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectRespositoryTest {

    @Autowired
    private ProjectRespository projectRespository;

    @Test
    public void findOne(){
        Project one = projectRespository.findOne(1);
        System.out.println(one.toString());
    }

    @Test
    public void findAll(){
        projectRespository.findAll();
    }

    @Test
    public void saveTest() {
//        Project project = projectRespository.findOne(4);

        Project project = new Project();
        project.setProjectName("测5");
        project.setProjectDesc("test3");

        Date date = new Date("2018/09/21");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        simpleDateFormat.format(date);
        project.setStartdate(date);

        Project project1 = projectRespository.save(project);
        Assert.assertNotNull(project1);
//        Assert.assertNotEquals(null,project1);
    }

    @Test
    public void delete(){
        Project project = new Project();
        project.setProjectId(8);
        projectRespository.delete(project.getProjectId());
    }

    @Test
    public void findProjectsByProject_state(){
        projectRespository.findProjectsByProjectState(1);
//        Assert.assertNotEquals(0,state.size());
//        for (Project project : state) {
//            System.out.println(project);
//        }
    }
}