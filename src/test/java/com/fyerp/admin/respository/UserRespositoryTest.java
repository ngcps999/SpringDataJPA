/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：UserRespositoryTest.java
 * 作者：xuda
 * 时间：18-4-13 下午2:18
 *
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.Permission;
import com.fyerp.admin.domain.Role;
import com.fyerp.admin.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: xuda
 * @Date: 2018/4/13
 * @Time: 下午2:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRespositoryTest {

    @Autowired
    private UserRespository userRespository;

    User user = new User();
    Role role = new Role();



    @Test
    public void findByUsername() {
        User admin = userRespository.findUserByUsername("admin");
        Assert.assertNotNull(admin);
        System.out.println(admin);
    }

//    @Test
//    public void addUser(){
//        user.setUserId(3l);
//        user.setName("xd1");
//        user.setUsername("xd2");
//        user.setPassword("123");
//
//        userRespository.save(user);
//    }
}