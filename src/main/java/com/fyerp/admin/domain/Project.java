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
import com.fyerp.admin.utils.BeanComparator;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @Author: xuda
 * @Date: 2018/4/3
 * @Time: 下午3:51
 * 项目实体
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
@Data
//@Document(indexName="index_entity", type="tstype")
public class Project implements Serializable {

    /**
     * 项目id
     */
    @Id
    @GeneratedValue
    @JsonProperty(value = "id")
    private Integer projectId;

    /**
     * 项目名称
     */
    @JsonProperty("name")
    private String projectName;

    @Transient
    @JsonProperty(defaultValue = "project")
    @ApiModelProperty(allowableValues = "project")
    private String type = "project";


    /**
     * 项目计划开始时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date planStartDate;

    /**
     * 项目计划完成时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date planEndDate;

    /**
     * 项目实际开始时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date realStartDate;

    /**
     * 项目实际完成时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
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
     * 项目分类
     */
    private String projectCategory;

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
     * 项目阶段
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
    private Integer projectState;

    /**
     * 项目描述
     */
    @JsonProperty("description")
    private String projectDesc;

    private Integer strategy;

    @Transient
    private String orderTask;

    @Transient
    private Integer taskOrderDirection;

    @ApiModelProperty(hidden = true)
    @Transient
    private List<Task> taskList;

    /**
     * 项目对应多个部门
     */
    @JsonIgnore
    @ManyToMany(targetEntity = Department.class,cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "ProjectDepartment", joinColumns = {@JoinColumn(name = "projectId")}, inverseJoinColumns = {@JoinColumn(name = "departmentId")})
    private Set<Department> departments = new HashSet<>();

    /**
     * 任务
     */
    @JsonProperty(value = "children")
    @ManyToMany(targetEntity = Task.class,cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @OrderBy(" taskId desc")
    @JoinTable(name = "ProjectTask", joinColumns = {@JoinColumn(name = "projectId")}, inverseJoinColumns = {@JoinColumn(name = "taskId")})
    private Set<Task> tasks = new HashSet<>();

    /**
     * 创建时间
     */
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("creationDate")
    private Date createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("updatedDate")
    private Date updateTime;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    private Contract contract;


    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;



    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
//        if(tasks != null){
//            taskList = new ArrayList<Task>(tasks);
//            taskList.sort(new Comparator<Task>() {
//                @Override
//                public int compare(Task o1, Task o2) {
//
//                    if(o1.getTaskId().intValue() > o2.getTaskId().intValue()){
//                        return 1;
//                    }else{
//                        return -1;
//                    }
//                }
//            });
//        }
        if (taskOrderDirection != null && taskOrderDirection.intValue() != 0){
            sortChild(orderTask,taskOrderDirection.intValue() > 0 ? 1 : -1);
        }

    }

    public void sortChild(String keyword,int orderDirection){
        if(tasks != null){
            taskList = new ArrayList<Task>(tasks);
           Collections.sort(taskList,new BeanComparator(keyword,orderDirection));
        }
    }

    public Project() {
    }

}
