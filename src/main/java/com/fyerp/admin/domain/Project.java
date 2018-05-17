/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：Project.java
 * 作者：xuda
 * 时间：18-4-3 下午3:51
 *
 */

package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.enums.ProjectStatusEnum;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xuda
 * @Date: 2018/4/3
 * @Time: 下午3:51
 * 项目实体
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
//@Data
//@Document(indexName="index_entity", type="tstype")
public class Project {

    /**
     * 无参构造
     */
    public Project() {
    }

    public Project(String projectName, Date planStartDate, Date planEndDate, Date realStartDate, Date realEndDate, String equipment, String flyPlatform, String flyHeight, String aeroRatio, String areoArea, String map, Integer projectState, String projectDesc, Date createTime, Date updateTime) {
        this.projectName = projectName;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;
        this.realStartDate = realStartDate;
        this.realEndDate = realEndDate;
        this.equipment = equipment;
        this.flyPlatform = flyPlatform;
        this.flyHeight = flyHeight;
        this.aeroRatio = aeroRatio;
        this.areoArea = areoArea;
        this.map = map;
        this.projectState = projectState;
        this.projectDesc = projectDesc;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /**
     * 项目id
     */
    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Integer projectId;

    /**
     * 项目名称
     */
    @JsonProperty("name")
    private String projectName;

    /**
     * 项目计划开始时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date planStartDate;

    /**
     * 项目计划完成时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date planEndDate;

    /**
     * 项目实际开始时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date realStartDate;

    /**
     * 项目实际完成时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date realEndDate;

//    /**
//     * 项目类型编号
//     */
//    private Integer categoryType;
    /**
     * 使用设备
     */
    private String equipment;

    /**
     * 项目内容
     */
    private String projectContent;

    /**
     * 项目范围
     */
    private String projectScope;

    /**
     * 技术要求
     */
    private String techReqs;

    /**
     * 成果要求
     */
    private String resultsReqs;

    /**
     * 工期要求
     */
    private Integer workDay;

    /**
     * 阶段
     */
    private String projectPhase;

    /**
     * 飞行平台
     */
    private String flyPlatform;

    /**
     * 飞行高度
     */
    private String flyHeight;

    /**
     * 航摄分辨率
     */
    private String aeroRatio;

    /**
     * 航摄面积
     */
    private String areoArea;

    /**
     * 地图
     */
    private String map;

    /**
     * 优先级（1最大100最小）
     */
    private Integer priority;

    /**
     * 项目状态：0未进行，1正在进行，2遇到问题
     */
    @JsonProperty("status")
    private Integer projectState = ProjectStatusEnum.DOING.getCode();

    /**
     * 项目描述
     */
    @JsonProperty("describe")
    private String projectDesc;

    /**
     * 项目对应多个部门
     */
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "projectId")
//    private Set<Department> departments = new HashSet<>();

    /**
     * 任务
     */
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId")
    private Set<Task> tasks = new HashSet<>();

    @JsonIgnore
    @ManyToOne(targetEntity = ProjectCategory.class, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    private ProjectCategory projectCategory;

    /**
     * 创建时间
     */
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date updateTime;



    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    @Required
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getPlanStartDate() {
        return planStartDate;
    }

    @Required
    public void setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
    }

    public Date getPlanEndDate() {
        return planEndDate;
    }

    @Required
    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    public Date getRealStartDate() {
        return realStartDate;
    }

    public void setRealStartDate(Date realStartDate) {
        this.realStartDate = realStartDate;
    }

    public Date getRealEndDate() {
        return realEndDate;
    }

    public void setRealEndDate(Date realEndDate) {
        this.realEndDate = realEndDate;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getFlyPlatform() {
        return flyPlatform;
    }

    public void setFlyPlatform(String flyPlatform) {
        this.flyPlatform = flyPlatform;
    }

    public String getFlyHeight() {
        return flyHeight;
    }

    public void setFlyHeight(String flyHeight) {
        this.flyHeight = flyHeight;
    }

    public String getAeroRatio() {
        return aeroRatio;
    }

    public void setAeroRatio(String aeroRatio) {
        this.aeroRatio = aeroRatio;
    }

    public String getAreoArea() {
        return areoArea;
    }

    public void setAreoArea(String areoArea) {
        this.areoArea = areoArea;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getProjectState() {
        return projectState;
    }

    @Required
    public void setProjectState(Integer projectState) {
        this.projectState = projectState;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
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

    public String getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent;
    }

    public String getProjectScope() {
        return projectScope;
    }

    public void setProjectScope(String projectScope) {
        this.projectScope = projectScope;
    }

    public String getTechReqs() {
        return techReqs;
    }

    public void setTechReqs(String techReqs) {
        this.techReqs = techReqs;
    }

    public String getResultsReqs() {
        return resultsReqs;
    }

    public void setResultsReqs(String resultsReqs) {
        this.resultsReqs = resultsReqs;
    }

    public Integer getWorkDay() {
        return workDay;
    }

    public void setWorkDay(Integer workDay) {
        this.workDay = workDay;
    }

    public String getProjectPhase() {
        return projectPhase;
    }

    public void setProjectPhase(String projectPhase) {
        this.projectPhase = projectPhase;
    }

    public ProjectCategory getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(ProjectCategory projectCategory) {
        this.projectCategory = projectCategory;
    }

//    public Set<Department> getDepartments() {
//        return departments;
//    }
//
//    public void setDepartments(Set<Department> departments) {
//        this.departments = departments;
//    }
}
