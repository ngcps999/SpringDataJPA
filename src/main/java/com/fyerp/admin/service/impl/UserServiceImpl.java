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

import com.fyerp.admin.domain.Department;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.domain.dto.DepartmentDTO;
import com.fyerp.admin.domain.dto.UserDTO;
import com.fyerp.admin.respository.DepartmentRespository;
import com.fyerp.admin.respository.UserRespository;
import com.fyerp.admin.service.DepartmentService;
import com.fyerp.admin.service.UserService;
import com.fyerp.admin.utils.BeanUtils;
import com.fyerp.admin.utils.convert.User2UserDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xuda
 * @Date: 2018/4/11
 * @Time: 下午3:02
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserRespository userRespository;

    @Override
    public User findByUsername(String username) {
        System.out.println("UserServiceImpl.findByUsername()");
        return userRespository.findUserByUsername(username);
    }

    @Override
    public UserDTO findOneDTO(Long userId) {
        User user = userRespository.findOne(userId);
        UserDTO userDTO = User2UserDTOConverter.convert(user);
        return userDTO;
    }

    @Override
    public User findOne(Long userId) {
        return userRespository.findOne(userId);
    }

    @Override
    public List<User> findAll() {
        return userRespository.findAll();
    }

    @Override
    public List<User> findAll(Sort sort) {
        return userRespository.findAll(sort);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRespository.findAll(pageable);
    }

    @Override
    public UserDTO saveAndFlushDTO(User user) {
        User flush = userRespository.saveAndFlush(user);
        UserDTO userDTO = User2UserDTOConverter.convert(flush);
        return userDTO;
    }

    @Override
    public User saveAndFlush(User user) {
        return userRespository.saveAndFlush(user);
    }

    @Override
    public List<User> saveAndFlush(List<User> users) {
        for (User user : users) {
            users.add(userRespository.saveAndFlush(user));
        }
        return users;
    }


    @Override
    public UserDTO update(User user) {
        User one = userRespository.findOne(user.getUserId());
        BeanUtils.copyProperties(user,one);
        User save = userRespository.saveAndFlush(one);
        UserDTO userDTO = User2UserDTOConverter.convert(save);
        return userDTO;
    }

    @Override
    public List<UserDTO> saveDTOList(List<User> users) {
        List<User> userList = userRespository.save(users);
        List<UserDTO> userDTOList = User2UserDTOConverter.convert(userList);
        return userDTOList;
    }

    @Override
    public User save(User user) {
        return userRespository.save(user);
    }

    @Override
    public List<User> save(List<User> users) {
        return userRespository.save(users);
    }

    @Override
    public void delete(Long userId) {
        userRespository.delete(userId);
    }

}
