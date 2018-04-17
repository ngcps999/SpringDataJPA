/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：Permission.java
 * 作者：xuda
 * 时间：18-4-11 下午2:33
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
 * @Time: 下午2:33
 */
@Entity
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 8787425039423616077L;
    /**
     * 主键Id
     */
    @Id
    @GeneratedValue
    private Long permissionId;

    /**
     * 名称
     */
    private String name;

    /**
     * 资源类型[menu|button]
     */
    @Column(columnDefinition = "enum('menu','button')")
    private String resourceType;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
     */
    private String permission;

    /**
     * 父级编号
     */
    private String parentId;

    /**
     * 父编号列表
     */
    private String parentIds;

    /**
     * 是否可用
     */
    private Boolean available = Boolean.FALSE;

    /**
     * 角色-权限多对多关系
     */
    @ManyToMany
    @JoinTable(name = "RolePermission",joinColumns={@JoinColumn(name = "permissionId")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<Role> roles;


}
