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

public interface UserService {
	
	/**通过username查找用户信息;*/
	public User findByUsername(String username);

}
