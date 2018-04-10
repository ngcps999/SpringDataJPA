/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：Func.java
 * 作者：xuda
 * 时间：18-4-10 上午9:42
 *
 */

package com.fyerp.admin.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: xuda
 * @Date: 2018/4/10
 * @Time: 上午9:42
 */
@Entity
@DynamicUpdate
@Data
public class Func {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String desc;
    private String url;
    private Integer pid;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "RoleFunc", joinColumns = {@JoinColumn(name = "funcId")}, inverseJoinColumns = {
            @JoinColumn(name = "roleId")})
    private List<Role> roles;

    public Func() {
    }

    public Func(String name, String desc, String url, Integer pid) {
        this.name = name;
        this.desc = desc;
        this.url = url;
        this.pid = pid;
    }
}
