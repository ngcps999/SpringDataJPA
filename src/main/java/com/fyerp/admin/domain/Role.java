/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：Role.java
 * 作者：xuda
 * 时间：18-4-11 下午2:15
 *
 */

package com.fyerp.admin.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: xuda
 * @Date: 2018/4/11
 * @Time: 下午2:15
 */
@Entity
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = -58106289614839772L;
    /**
     * 角色编号
     */
    @Id
    @GeneratedValue
    private Long roleId;

    /**
     * 角色标识，程序中判断使用,如"admin",这个是唯一的:
     */
    private String role;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 角色是否可用，如果不可用将不会添加给用户
     */
    private Boolean available = Boolean.FALSE;

    /**
     * 角色-权限多对多关系
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "RolePermission",joinColumns = {@JoinColumn(name = "roleId")},inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<Permission> permissions;

    /**
     * 用户-角色多对多关系,一个角色对应多个用户
     */
    @ManyToMany
    @JoinTable(name = "UserRole",joinColumns = {@JoinColumn(name = "roleId")},inverseJoinColumns = {@JoinColumn(name = "userId")})
    private List<User> users;

}
