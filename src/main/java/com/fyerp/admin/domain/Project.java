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
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
@Data
//@Document(indexName="index_entity", type="tstype")
public class Project {

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
    @JsonProperty(defaultValue = "ProjectCategory")
    @ApiModelProperty(allowableValues = "Project")
    private String type = "Project";


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
    @JsonProperty("description")
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
    @JsonProperty(value = "children")
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId")
    private List<Task> tasks;

    @JsonIgnore
    @ManyToOne(targetEntity = ProjectCategory.class, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    private ProjectCategory projectCategory;

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

    public Project() {
    }
}
