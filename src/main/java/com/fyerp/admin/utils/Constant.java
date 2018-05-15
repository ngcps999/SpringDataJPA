/*
 * 作者：xuda
 * 创建时间：18-5-15 上午9:00
 * 模块名称：admin
 */

package com.fyerp.admin.utils;

import org.springframework.data.domain.Sort;

/**
 * 常量
 */
public class Constant {

    public static String CREATE_TIME = "createTime";
    public static String PRIORITY = "priority";

    public static Sort SORT_CREATE_TIME = new Sort(Sort.Direction.DESC, CREATE_TIME);
}
