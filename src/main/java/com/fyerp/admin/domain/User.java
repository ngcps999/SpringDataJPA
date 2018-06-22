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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.domain.base.BaseModel;
import com.fyerp.admin.enums.DepartmentEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @Author: xuda
 * @Date: 2018/4/11
 * @Time: 下午2:07
 */
@Entity
@Data
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable{

    /**
     * 用户Id
     */
    @Id
    @GeneratedValue
//    @GenericGenerator(name = "generator",strategy = "assigned")
    @JsonProperty(value = "id")
    private Long userId;

    /**
     * 用户账号
     */
//    @NotBlank(message = "用户名必填")
    private String username;

    /**
     * 姓名
     */
//    @NotBlank(message = "姓名必填")
    private String name;

    @Transient
    @JsonProperty(value = "type", defaultValue = "user")
    @ApiModelProperty(allowableValues = "user")
    private String type = "user";

    /**
     * 性别
     */
//    @NotBlank
    private String gender;

    /**
     * 密码
     */
//    @NotBlank
    private String password;

    /**
     * 用户状态,0:用户未输入验证码, 1:正常状态,2：用户被锁定.
     */
    @JsonProperty("status")
    private Integer state;

    private Integer strategy;

    /**
     * 一个用户具有多个角色
     */
////    @NotEmpty
//            @JsonIgnore
    @JsonProperty("children")
    @ManyToMany(targetEntity = Role.class,cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)//立即从数据库中加载数据；
    @JoinTable(name = "UserRole", joinColumns = {@JoinColumn(name = "userId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private Set<Role> roles = new HashSet<>();

//
//    @JsonIgnore
////    @JsonProperty("children")
//    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
//    @JoinTable(name = "DepartmentUser",joinColumns = {@JoinColumn(name = "userId")},inverseJoinColumns = {@JoinColumn(name = "departmentId")})
//    private List<Department> departments;

//    @JsonIgnore
//    @ManyToMany
//    @JoinTable(name = "TaskUser",joinColumns = {@JoinColumn(name = "userId")},inverseJoinColumns = {@JoinColumn(name = "taskId")})
//    private List<Task> tasks;

    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @JsonProperty("creationDate")
    private Date createTime;
    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @JsonProperty("updatedDate")
    private Date updateTime;

    public User() {
    }


}
