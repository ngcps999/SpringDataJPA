/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：Role.java
 * 作者：xuda
 * 时间：18-4-10 上午9:42
 *
 */

package com.fyerp.admin.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: xuda
 * @Date: 2018/4/10
 * @Time: 上午9:42
 */
@Entity
@DynamicUpdate
@Data
public class Role {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String code;
    private String description;

    // 角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "RoleFunc", joinColumns = { @JoinColumn(name = "roleId") }, inverseJoinColumns = {
            @JoinColumn(name = "funcId") })
    private List<Func> funcs;

    // 用户 - 角色关系定义;
    @ManyToMany
    @JoinTable(name = "UserRole", joinColumns = { @JoinColumn(name = "roleId") }, inverseJoinColumns = {
            @JoinColumn(name = "userId") })
    private List<User> users;// 一个角色对应多个用户


    public Role() {
    }

    public Role(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }
}
