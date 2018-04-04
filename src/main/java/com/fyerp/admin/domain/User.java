/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：User.java
 * 作者：xuda
 * 时间：18-4-3 下午3:27
 *
 */

package com.fyerp.admin.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


/**
 * @Author: xuda
 * @Date: 2018/4/3
 * @Time: 下午3:51
 * 用户表
 */
@Entity
@Data
public class User {

    /**
     * 用户id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户性别
     */
    private Character sex;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 用户生日
     */
    private Date birthday;

    /**
     * 用户电话
     */
    private String telephone;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createtime;


}
