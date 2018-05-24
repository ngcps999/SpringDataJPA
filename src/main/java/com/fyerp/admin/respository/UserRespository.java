/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：UserRespository.java
 * 作者：xuda
 * 时间：18-4-10 下午3:49
 *
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.Department;
import com.fyerp.admin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User持久化类
 *
 * @Author: xuda
 * @Date: 2018/4/10
 * @Time: 下午3:49
 */
public interface UserRespository extends JpaRepository<User,Long> {

    /**
     * 通过username查找用户信息
     */
    public User findUserByUsername(String username);

}

