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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: xuda
 * @Date: 2018/4/11
 * @Time: 下午2:15
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class Role implements Serializable {

    private static final long serialVersionUID = -58106289614839772L;
    /**
     * 角色编号
     */
    @Id
    @GeneratedValue
    @JsonProperty(value = "id", index = 0)
    private Long roleId;

    /**
     * 角色标识，程序中判断使用,如"admin",这个是唯一的:
     */
    @JsonProperty("name")
    private String role;

    @Transient
    @JsonProperty(defaultValue = "role")
    @ApiModelProperty(allowableValues = "role")
    private String type = "role";

    /**
     * 角色描述
     */
    private String description;

    private Integer strategy;

//    /**
//     * 角色是否可用，如果不可用将不会添加给用户
//     */
//    private Boolean available = Boolean.FALSE;·

    /**
     * 角色-权限多对多关系
     */
    @JsonProperty(value = "children")
    @ManyToMany(targetEntity = Permission.class,cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name = "RolePermission", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private Set<Permission> permissions =new HashSet<>();

    /**
     * 用户-角色多对多关系,一个角色对应多个用户
     */
//    @JsonIgnore
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "UserRole", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "userId")})
//    private List<User> users;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("creationDate")
    private Date createTime;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("updatedDate")
    private Date updateTime;

}
