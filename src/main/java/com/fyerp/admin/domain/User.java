/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：UserInfo.java
 * 作者：xuda
 * 时间：18-4-11 下午2:07
 *
 */

package com.fyerp.admin.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: xuda
 * @Date: 2018/4/11
 * @Time: 下午2:07
 */
@Entity
@Data
public class User {
    /**
     * 用户Id
     */
    @Id
    @GeneratedValue
    private Long uid;

    /**
     * 用户账号
     */
    @Column(unique = true)
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密密码的盐
     */
    private String salt;

    /**
     * 用户状态,0:用户未输入验证码, 1:正常状态,2：用户被锁定.
     */
    private Byte state;

    /**
     * 一个用户具有多个角色
     */
    @ManyToMany(fetch = FetchType.EAGER)//立即从数据库中加载数据；
    @JoinTable(name = "UserRole",joinColumns = {@JoinColumn(name = "uid")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<Role> roles;

    /**
     * 密码盐
     * @return
     */
    public String getCredentialsSalt() {
        return this.username+this.salt;
    }
}
