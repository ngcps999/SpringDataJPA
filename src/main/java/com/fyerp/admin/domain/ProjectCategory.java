/*
 * 作者：xuda
 * 创建时间：18-5-14 上午8:47
 * 模块名称：admin
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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
@Data
public class ProjectCategory {

    /**
     * 类目id
     */
    @Id
    @GeneratedValue
    @JsonProperty(value = "id")
    private Integer categoryId;

    /**
     * 类目名称
     */
    @JsonProperty("name")
    private String categoryName;

    /**
     * 类目描述
     */
    @JsonProperty("description")
    private String categoryDesc;

    private Integer strategy;

    @Transient
    @JsonProperty(defaultValue = "projectCategory")
    @ApiModelProperty(allowableValues = "projectCategory")
    private String type = "projectCategory";

    @ManyToMany(targetEntity = Project.class,cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "ProjectCategoryProject", joinColumns = {@JoinColumn(name = "categoryId")}, inverseJoinColumns = {@JoinColumn(name = "projectId")})
    @JsonProperty(value = "children")
    private Set<Project> projects =new HashSet<>();

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("creationDate")
    private Date createTime;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("updatedDate")
    private Date updateTime;

}
