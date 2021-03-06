/*
 * 作者：xuda
 * 创建时间：18-6-7 下午2:01
 * 模块名称：admin
 */

package com.fyerp.admin.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.domain.Plan;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xuda
 * @Date: 2018/4/10
 * @Time: 上午9:42
 */
@EntityListeners(AuditingEntityListener.class)
@Data
public class TaskVO {

    @Id
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


//    /**
//     * 计划
//     */
//    @OneToMany(targetEntity = Plan.class,cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
//    @JoinColumn(name = "taskId")
//    @JsonProperty(value = "children")
//    private Set<Plan> plans = new HashSet<>();


    /**
     * 一个任务具有多个部门参与
     */
//    @ManyToMany(fetch = FetchType.EAGER)//立即从数据库中加载数据；
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

    public TaskVO() {
    }
}
