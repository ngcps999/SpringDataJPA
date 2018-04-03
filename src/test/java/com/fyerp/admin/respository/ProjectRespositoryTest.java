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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        Project one = projectRespository.findOne(4);

        System.out.println(one.toString());
    }

    @Test
    public void saveTest() {
        Project project = projectRespository.findOne(4);

//        Project project = new Project();
        project.setProject_name("测5");
//        project.setProject_desc("test3");
//        Date date = new Date("2018/09/21");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//        simpleDateFormat.format(date);
//        project.setStartdate(date);
        projectRespository.save(project);
    }

}