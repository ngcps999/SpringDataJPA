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
import com.fyerp.admin.domain.dto.UserDTO;
import com.fyerp.admin.respository.UserRepository;
import com.fyerp.admin.service.UserService;
import com.fyerp.admin.utils.BeanUtils;
import com.fyerp.admin.utils.MD5Util;
import com.fyerp.admin.utils.convert.User2UserDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xuda
 * @Date: 2018/4/11
 * @Time: 下午3:02
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        System.out.println("UserServiceImpl.findByUsername()");
        return userRepository.findUserByUsername(username);
    }

    @Override
    public UserDTO findOneDTO(Long userId) {
        User user = userRepository.findOne(userId);
        UserDTO userDTO = User2UserDTOConverter.convert(user);
        return userDTO;
    }

    @Override
    public User findOne(Long userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(List<Long> userIds) {
        return userRepository.findAll(userIds);
    }

    @Override
    public List<UserDTO> findAllDTD(List<Long> userIds) {
        List<User> users = userRepository.findAll(userIds);
        List<UserDTO> userDTOS =new ArrayList<>();
        BeanUtils.copyNotNullProperties(users,userDTOS);
        return userDTOS;
    }

    @Override
    public List<User> findAll(Sort sort) {
        return userRepository.findAll(sort);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserDTO saveAndFlushDTO(User user) {
        User flush = userRepository.saveAndFlush(user);
        UserDTO userDTO = User2UserDTOConverter.convert(flush);
        return userDTO;
    }

    @Override
    public User saveAndFlush(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> saveAndFlush(List<User> users) {
        for (User user : users) {
            users.add(userRepository.saveAndFlush(user));
        }
        return users;
    }


    @Override
    public UserDTO update(User user) {
        User one = userRepository.findOne(user.getUserId());
        BeanUtils.copyProperties(user,one);
        User save = userRepository.saveAndFlush(one);
        UserDTO userDTO = User2UserDTOConverter.convert(save);
        return userDTO;
    }

    @Override
    public List<UserDTO> saveDTOList(List<User> users) {
        List<User> userList = userRepository.save(users);
        List<UserDTO> userDTOList = User2UserDTOConverter.convert(userList);
        return userDTOList;
    }

    @Override
    public User save(User user) {
        if(user.getUserId() != 0 && !StringUtils.isEmpty(user.getPassword())){
            user.setPassword(MD5Util.getMD5(user.getPassword()));
        }

        User save = userRepository.save(user);
        save.setPassword("");
        return save;
    }

    @Override
    public List<User> save(List<User> users) {
        return userRepository.save(users);
    }

    @Override
    public void delete(Long userId) {
        userRepository.delete(userId);
    }

    @Override
    public User findUserByNameAndPwd(String userName, String password) {
        List<BigInteger> ids = userRepository.findUserIdsByNameAndPwd(userName,MD5Util.getMD5(password));
        if(ids != null && ids.size() > 0){
            return userRepository.findOne(ids.get(0).longValue());
        }
        return null;
    }

    @Override
    public List<Long> findDepartmentByUser(Long userId) {
        return userRepository.findDepartmentIdsByUser(userId);
    }

}
