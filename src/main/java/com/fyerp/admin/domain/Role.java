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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: xuda
 * @Date: 2018/4/11
 * @Time: 下午2:15
 */
@Entity
//@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class Role implements Serializable {

    private static final long serialVersionUID = -58106289614839772L;
    /**
     * 角色编号
     */
    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Long roleId;

    /**
     * 角色标识，程序中判断使用,如"admin",这个是唯一的:
     */
    @JsonProperty("name")
    private String role;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 角色是否可用，如果不可用将不会添加给用户
     */
    private Boolean available = Boolean.FALSE;

    public Role() {
    }

    public Role(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }
    /**
     * 角色-权限多对多关系
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "RolePermission",joinColumns = {@JoinColumn(name = "roleId")},inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<Permission> permissions;

    /**
     * 用户-角色多对多关系,一个角色对应多个用户
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "UserRole",joinColumns = {@JoinColumn(name = "roleId")},inverseJoinColumns = {@JoinColumn(name = "userId")})
    private List<User> users;

    @JsonIgnore
    @CreatedDate
    private Date createTime;
    @JsonIgnore
    @LastModifiedDate
    private Date updateTime;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }
}
