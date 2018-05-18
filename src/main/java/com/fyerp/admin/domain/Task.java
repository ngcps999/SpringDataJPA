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
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
public class Task {

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Long taskId;
    @JsonProperty("name")
    private String taskName;
    @JsonProperty("description")
    private String taskDesc;
    @JsonProperty("status")
    private String taskState;
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

    @JsonIgnore
    @ManyToOne(targetEntity = Project.class, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId")
    private Project project;

    /**
     * 计划
     */
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "taskId")
    private Set<Plan> plans = new HashSet<>();


    /**
     * 一个任务具有多个部门参与
     */
//    @ManyToMany(fetch = FetchType.EAGER)//立即从数据库中加载数据；
//    @JoinTable(name = "TaskDepartment", joinColumns = {@JoinColumn(name = "taskId")}, inverseJoinColumns = {@JoinColumn(name = "departmentId")})
//    private Set<Department> departments = new HashSet<>();

    @JsonIgnore
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @JsonProperty("creationDate")
    private Date createTime;
    @JsonIgnore
    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @JsonProperty("updatedDate")
    private Date updateTime;

    public Task() {
    }

    public Task(String taskName, String taskDesc, String taskState, Date taskPlanStartDate, Date taskRealStartDate, Date taskRealEndDate, Date taskPlanEndDate) {
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.taskState = taskState;
        this.taskPlanStartDate = taskPlanStartDate;
        this.taskRealStartDate = taskRealStartDate;
        this.taskRealEndDate = taskRealEndDate;
        this.taskPlanEndDate = taskPlanEndDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                ", taskState='" + taskState + '\'' +
                ", taskPlanStartDate=" + taskPlanStartDate +
                ", taskRealStartDate=" + taskRealStartDate +
                ", taskRealEndDate=" + taskRealEndDate +
                ", taskPlanEndDate=" + taskPlanEndDate +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    @Required
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskState() {
        return taskState;
    }

    @Required
    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public Date getTaskPlanStartDate() {
        return taskPlanStartDate;
    }

    public void setTaskPlanStartDate(Date taskPlanStartDate) {
        this.taskPlanStartDate = taskPlanStartDate;
    }

    public Date getTaskRealStartDate() {
        return taskRealStartDate;
    }

    public void setTaskRealStartDate(Date taskRealStartDate) {
        this.taskRealStartDate = taskRealStartDate;
    }

    public Date getTaskRealEndDate() {
        return taskRealEndDate;
    }

    public void setTaskRealEndDate(Date taskRealEndDate) {
        this.taskRealEndDate = taskRealEndDate;
    }

    public Date getTaskPlanEndDate() {
        return taskPlanEndDate;
    }

    public void setTaskPlanEndDate(Date taskPlanEndDate) {
        this.taskPlanEndDate = taskPlanEndDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

//    public Set<Department> getDepartments() {
//        return departments;
//    }
//
//    @Required
//    public void setDepartments(Set<Department> departments) {
//        this.departments = departments;
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

    public Set<Plan> getPlans() {
        return plans;
    }

    public void setPlans(Set<Plan> plans) {
        this.plans = plans;
    }
}
