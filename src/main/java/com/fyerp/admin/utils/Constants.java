/*
 * 作者：xuda
 * 创建时间：18-6-7 上午9:59
 * 模块名称：admin
 */

package com.fyerp.admin.utils;

public class Constants {

	/**已删除*/
	public static final int DEL_YES = 1;
	
	/**未删除*/
	public static final int DEL_NO = 0;
	
	
	/**普通用户*/
	public static final String ROLE_CODE_USER = "user";
	/**操作员*/
	public static final String ROLE_CODE_OPERATOR = "operator";
	/**管理员*/
	public static final String ROLE_CODE_ADMIN = "admin";

	/**
	 * PROJECT strategy 参数
	 */
	public static final int STRATEGY_INSERT = 0;
	public static final int STRATEGY_UPDATE = 1;
	public static final int STRATEGY_DELETE = 2;

	public static final String LOGIN_SUCCESS = "登录成功！";
	public static final String LOGIN_FAILURE = "登录失败！";
	public static final String DELETE_SUCCESS = "删除成功！";
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
}
