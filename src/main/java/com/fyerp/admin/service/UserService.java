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

import java.util.List;

public interface UserService {
	
	/**通过username查找用户信息;*/
	public User findByUsername(String username);

	/**
	 * 查询单个用户
	 * @param userId
	 * @return
	 */
	User findOne(Long userId);

	/**
	 * 查询所有用户
	 *
	 * @return
	 */
	List<User> findAll();

	/**
	 * 新增/更新用户
	 *
	 * @param user
	 * @return
	 */
	User save(User user);

	/**
	 * 删除用户
	 *
	 * @param userId
	 */
	void delete(Long userId);

}
