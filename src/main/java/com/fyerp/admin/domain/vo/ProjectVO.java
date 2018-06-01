/*
 * 作者：xuda
 * 创建时间：18-5-14 下午12:49
 * 模块名称：admin
 */

package com.fyerp.admin.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.domain.ProjectCategory;
import com.fyerp.admin.domain.Task;
import com.fyerp.admin.enums.ProjectStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ProjectVO {


    /**
     * 项目id
     */
    @Id
    @JsonProperty(value = "id")
    private Integer projectId;

    /**
     * 项目名称
     */
    @JsonProperty("name")
    private String projectName;

//    @Transient
//    @JsonProperty(defaultValue = "project")
//    @ApiModelProperty(allowableValues = "project")
//    private String type = "project";


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

//    /**
//     * 任务
//     */
//    @JsonProperty(value = "children")
//    @OneToMany(targetEntity = Task.class,cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
//    @JoinColumn(name = "projectId")
//    private Set<Task> tasks = new HashSet<>();

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

    public ProjectVO() {
    }

}
