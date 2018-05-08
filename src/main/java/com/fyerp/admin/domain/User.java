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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.enums.DepartmentEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
public class User {

    private static final long serialVersionUID = -8454698376979101464L;
    /**
     * 用户Id
     */
    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Long userId;

    /**
     * 用户账号
     */
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
     * 用户状态,0:用户未输入验证码, 1:正常状态,2：用户被锁定.
     */
    @JsonProperty("status")
    private Integer state;

    /**
     * 一个用户具有多个角色
     */
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)//立即从数据库中加载数据；
    @JoinTable(name = "UserRole", joinColumns = {@JoinColumn(name = "userId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private Set<Role> roles = new HashSet<>();

//    @JsonIgnore
//    @ManyToOne(targetEntity = Project.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "projectId")
//    private Project menber;

    @JsonIgnore
    @ManyToOne(targetEntity = Department.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "departmentId")
    private Department department;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "TaskUser",joinColumns = {@JoinColumn(name = "userId")},inverseJoinColumns = {@JoinColumn(name = "taskId")})
    private List<Task> tasks;

    @JsonIgnore
    @CreatedDate
    private Date createTime;
    @JsonIgnore
    @LastModifiedDate
    private Date updateTime;

    public User() {
    }

    public User(String username, String name, String password, Integer state, Department department, Date createTime, Date updateTime) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.state = state;
        this.department = department;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

//    public Project getMenber() {
//        return menber;
//    }
//
//    public void setMenber(Project menber) {
//        this.menber = menber;
//    }

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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                '}';
    }
}
