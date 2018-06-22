/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：Task.java
 * 作者：xuda
 * 时间：18-4-10 上午9:42
 *
 */

package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Author: xuda
 * @Date: 2018/4/10
 * @Time: 上午9:42
 */
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Data
@DynamicInsert
public class Task implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty(value = "id")
    private Long taskId;
    @JsonProperty("name")
    private String taskName;
    @Transient
    @JsonProperty(value = "type", defaultValue = "task")
    @ApiModelProperty(allowableValues = "task")
    private String type = "task";
    @JsonProperty("description")
    private String taskDesc;
    @JsonProperty("status")
    private Integer taskState;
    @JsonProperty("planStartDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date taskPlanStartDate;
    @JsonProperty("realStartDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date taskRealStartDate;
    @JsonProperty("realEndDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date taskRealEndDate;
    @JsonProperty("planEndDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date taskPlanEndDate;
    private Integer strategy;


    /**
     * 计划
     */
    @ManyToMany(targetEntity = Plan.class,cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(name = "TaskPlan", joinColumns = {@JoinColumn(name = "taskId")}, inverseJoinColumns = {@JoinColumn(name = "planId")})
    @JsonProperty(value = "children")
    private Set<Plan> plans = new HashSet<>();


    /**
     * 一个任务具有多个部门参与
     */
//    @JsonIgnore
//    @JsonProperty("departments")
//    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)//立即从数据库中加载数据；
//    @JoinTable(name = "TaskDepartment", joinColumns = {@JoinColumn(name = "taskId")}, inverseJoinColumns = {@JoinColumn(name = "departmentId")})
//    private Set<Department> departments = new HashSet<>();

    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @JsonProperty("creationDate")
    private Date createTime;
    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @JsonProperty("updatedDate")
    private Date updateTime;

    public Task() {
    }
}
