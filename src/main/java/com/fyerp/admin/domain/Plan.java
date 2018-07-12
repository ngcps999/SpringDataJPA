/*
 * 作者：xuda
 * 创建时间：18-4-20 下午4:28
 * 模块名称：admin
 */

package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.utils.MyComment;
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
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
@Data
public class Plan implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty(value = "id")
    private Integer planId;
    @JsonProperty("name")
    @MyComment("计划名称")
    private String planName;

    @Transient
    @JsonProperty(defaultValue = "plan")
    @ApiModelProperty(allowableValues = "plan")
    private String type = "plan";


    @JsonProperty("content")
    @MyComment("计划内容")
    private String planContent;

    /**
     * 项目计划开始时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @MyComment("项目计划开始时间")
    private Date planStartDate;

    /**
     * 项目计划完成时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @MyComment("项目计划完成时间")
    private Date planEndDate;

    /**
     * 项目实际开始时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @MyComment("计划实际开始时间")
    private Date realStartDate;

    /**
     * 项目实际完成时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @MyComment("计划实际完成时间")
    private Date realEndDate;

//    @JsonIgnore
//    @ManyToOne(targetEntity = Task.class, cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
//    @JoinColumn(name = "taskId")
//    private Task task;

    private Integer strategy;


    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @JsonProperty("creationDate")
    @MyComment("创建时间")
    private Date createTime;
    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @MyComment("更新时间")
    private Date updateTime;

}
