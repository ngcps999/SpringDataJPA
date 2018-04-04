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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: xuda
 * @Date: 2018/4/4
 * @Time: 上午10:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceImplTest {

    @Autowired
    private ProjectServiceImpl projectService;

    @Test
    public void findOne() {
        Project project = projectService.findOne(1);
        Assert.assertEquals(new Integer(1),project.getId());
    }

    @Test
    public void findAll() {

    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }
}