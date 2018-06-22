/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：UserService.java
 * 作者：xuda
 * 时间：18-4-11 下午3:02
 *
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.User;
import com.fyerp.admin.domain.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {
	
	/**通过username查找用户信息;*/
	public User findByUsername(String username);

	/**
	 * 查询单个用户
	 * @param userId
	 * @return
	 */
	UserDTO findOneDTO(Long userId);
	User findOne(Long userId);

	/**
	 * 查询所有用户
	 *
	 * @return
	 */
	List<User> findAll();
	List<User> findAll(List<Long> userIds);
	List<UserDTO> findAllDTD(List<Long> userIds);


	List<User> findAll(Sort sort);



	/**
	 * 查询所有用户(带分页)
	 * @return
	 */
	Page<User> findAll(Pageable pageable);

	/**
	 * 新增用户
	 *
	 * @param user
	 * @return
	 */
	User save(User user);
	List<User> save(List<User> users);
	User saveAndFlush(User user);
	List<User> saveAndFlush(List<User> user);
	UserDTO saveAndFlushDTO(User user);
	UserDTO update(User user);

	List<UserDTO> saveDTOList(List<User> users);

    /**
	 * 删除用户
	 *
	 * @param userId
	 */
	void delete(Long userId);


	User findUserByNameAndPwd(String userName,String password);

}
