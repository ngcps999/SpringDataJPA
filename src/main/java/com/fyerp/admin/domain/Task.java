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
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @Author: xuda
 * @Date: 2018/4/10
 * @Time: 上午9:42
 */
@Entity
@DynamicUpdate
//@Data
public class Task {

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Integer taskId;
    @JsonProperty("name")
    private String taskName;
    @JsonProperty("describe")
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
    @ManyToOne(targetEntity = Project.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId")
    private Project task;

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

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

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

    public Project getTask() {
        return task;
    }

    public void setTask(Project task) {
        this.task = task;
    }
}
