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

import com.fyerp.admin.domain.Permission;
import com.fyerp.admin.domain.Role;
import com.fyerp.admin.domain.Task;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.domain.dto.UserDTO;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.exception.UserException;
import com.fyerp.admin.respository.UserRepository;
import com.fyerp.admin.service.UserService;
import com.fyerp.admin.utils.BeanUtils;
import com.fyerp.admin.utils.Constants;
import com.fyerp.admin.utils.MD5Util;
import com.fyerp.admin.utils.convert.User2UserDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.*;

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
        if(user != null && !StringUtils.isEmpty(user.getPassword())){
            user.setPassword(MD5Util.securityPwd(user.getPassword()));
        }
        if(user.getUserId() == null || user.getUserId().longValue() == 0L){
            if(this.findByUsername(user.getUsername()) != null){
                throw new UserException(ResultEnum.USER_EXIST);
            }
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
        List<BigInteger> ids = userRepository.findUserIdsByNameAndPwd(userName,MD5Util.securityPwd(password));
        if(ids != null && ids.size() > 0){
            return userRepository.findOne(ids.get(0).longValue());
        }
        return null;
    }

    @Override
    public List<Long> findDepartmentByUser(Long userId) {
        return userRepository.findDepartmentIdsByUser(userId);
    }

    @Override
    public User saveUser(User user) {
        try {
            if(user.getUserId() != null && user.getUserId().intValue() > 0){
                //更新
                /*先将整个project整理出来，再整体入库*/
                User user1 = userRepository.findOne(user.getUserId());
//                1)整理task
                Set<Role> userRoles = user1.getRoles();
                if(user.getRoles() != null){
                    //迭代传入参数中的task.如果不同则放入要更新对象中
                    Set<Role> newRoles = new HashSet<>();
                    for(Role role : user.getRoles()){
                        //比较原先task与当前task的id，如果不同，插入
                        boolean isInsert = true;
                        Iterator<Role> roleIterator = userRoles.iterator();
                        while (roleIterator.hasNext()){
                            Role oldRole = roleIterator.next();
                            if(oldRole.getRoleId().intValue() == role.getRoleId().intValue()){
                                isInsert = false;
                                roleIterator.remove();
                                Role newRole = role;
                                //对修改的和原先的两个task处理合并成一个,需要合并子对象plan
                                if(newRole.getPermissions() != null){
                                    Set<Permission> newPermissions = new HashSet<>();
                                    for(Permission newPermission : newRole.getPermissions()){
                                        Iterator<Permission> permissionIterator = oldRole.getPermissions().iterator();
                                        boolean insertPlan = true;
                                        while (permissionIterator.hasNext()){
                                            Permission oldPermission = permissionIterator.next();
                                            if(newPermission.getPermissionId().longValue() == oldPermission.getPermissionId().longValue()){
                                                insertPlan = false;
                                                permissionIterator.remove();
                                                //替换
                                                newPermissions.add(newPermission);

                                                break;
                                            }
                                        }
                                        if(insertPlan){
                                            newPermissions.add(newPermission);
                                        }
                                    }
                                    oldRole.getPermissions().addAll(newPermissions);
                                    newRole.setPermissions(oldRole.getPermissions());
                                }else{
                                    newRole.setPermissions(oldRole.getPermissions());
                                }
                                newRoles.add(newRole);
                                break;
                            }
                        }
                        if(isInsert){
                            if(role.getPermissions() != null){
                                for(Permission permission : role.getPermissions()){
                                    if(permission.getPermissionId().longValue() != 0L){
                                        //新增task时，task中不允许带有已存在的plan
                                        throw new UserException(ResultEnum.PARAM_ERROR);
                                    }
                                }
                            }
                            role.setCreateTime(new Date());
                            newRoles.add(role);
                        }
                    }
                    userRoles.addAll(newRoles);
                }
                user.setRoles(userRoles);

                User save = userRepository.save(user);


                //处理以删除元素不返回，strategy参数为2就是删除
                if(save.getStrategy().intValue() == Constants.STRATEGY_DELETE){
                    return null;
                }else{
                    Set<Role> roleSet = new LinkedHashSet<>();
                    for(Role role : save.getRoles()){
                        if(role.getStrategy().intValue() != Constants.STRATEGY_DELETE){
                            Set<Permission> permissionSet = new LinkedHashSet<>();
                            for(Permission permission : role.getPermissions()){
                                if(permission.getStrategy() != Constants.STRATEGY_DELETE){
                                    permissionSet.add(permission);
                                }
                            }
                            role.setPermissions(permissionSet);
                            roleSet.add(role);
                        }
                    }
                    save.setRoles(roleSet);
                }
                save.setPassword("");
                return save;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        User save = userRepository.save(user);
        save.setPassword("");
        return save;
    }

}
