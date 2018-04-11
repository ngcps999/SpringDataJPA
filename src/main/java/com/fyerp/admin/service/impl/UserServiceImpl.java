/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：UserServiceImpl.java
 * 作者：xuda
 * 时间：18-4-11 下午3:03
 *
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.User;
import com.fyerp.admin.respository.UserRespository;
import com.fyerp.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xuda
 * @Date: 2018/4/11
 * @Time: 下午3:02
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRespository userRespository;

    @Override
    public User findByUsername(String username) {
        System.out.println("UserServiceImpl.findByUsername()");
        return userRespository.findByUsername(username);
    }
}
