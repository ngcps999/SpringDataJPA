/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：UserServiceImplTest.java
 * 作者：xuda
 * 时间：18-4-13 下午4:28
 *
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.User;
import com.fyerp.admin.respository.UserRespository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: xuda
 * @Date: 2018/4/13
 * @Time: 下午4:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserRespository userRespository;

    @Test
    public void findUserByUserName(){
        User admin = userRespository.findUserByUsername("admin");
        Assert.assertNotNull(admin);
    }

}