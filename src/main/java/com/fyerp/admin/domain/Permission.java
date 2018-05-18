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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Required;
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
 * @Time: 下午2:33
 */
@Entity
//@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class Permission implements Serializable {
    private static final long serialVersionUID = 8787425039423616077L;
    /**
     * 主键Id
     */
    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Long permissionId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 授权链接
     */
    private String url;

    /**
     * 权限描述
     */
    private String permission;

    /**
     * 父节点Id
     */
    private Integer parentId;

    /**
     * 角色-权限多对多关系
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "RolePermission",joinColumns={@JoinColumn(name = "permissionId")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<Role> roles;

    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @JsonProperty("creationDate")
    private Date createTime;
    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @JsonProperty("updatedDate")
    private Date updateTime;

    public Permission() {
    }

    public Permission(String name, String url, String permission, Integer parentId) {
        this.name = name;
        this.url = url;
        this.permission = permission;
        this.parentId = parentId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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
        return "Permission{" +
                "permissionId=" + permissionId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", permission='" + permission + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
