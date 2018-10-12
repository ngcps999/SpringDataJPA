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

import com.fyerp.admin.domain.User;
import com.fyerp.admin.respository.BaseRespository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * User持久化类
 *
 * @Author: xuda
 * @Date: 2018/4/10
 * @Time: 下午3:49
 */
public interface UserRepository extends BaseRepository<User,Long> {

    /**
     * 通过username查找用户信息
     */
    public User findUserByUsername(String username);

    @Query(value = "select user_id from user u where u.username = ?1 and u.password = ?2",nativeQuery = true)
    List<BigInteger> findUserIdsByNameAndPwd(String userName, String password);

    @Modifying
    @Transactional
    @Query(value = "select department_id from department_user where user_id = ?1",nativeQuery = true)
    List<Long> findDepartmentIdsByUser(Long userId);

}

