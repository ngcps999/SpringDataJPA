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
public class User  {

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
     * 部门
     */
    private String department = DepartmentEnum.YFB.getName();

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
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)//立即从数据库中加载数据；
    @JoinTable(name = "UserRole",joinColumns = {@JoinColumn(name = "userId")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @ManyToOne(targetEntity = Project.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId")
    private Project menber;


    public User() {
    }

    public User(String username, String name, String password, Integer state) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.state = state;
    }

    public User(String username, String name, String password, Integer state, Set<Role> roles) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.state = state;
        this.roles = roles;
    }

    public User(String username, String name, String password, Integer state, Set<Role> roles, Project menber) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.state = state;
        this.roles = roles;
        this.menber = menber;
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

    public Project getMenber() {
        return menber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setMenber(Project menber) {
        this.menber = menber;
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
