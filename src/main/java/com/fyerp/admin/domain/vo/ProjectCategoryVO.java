/*
 * 作者：xuda
 * 创建时间：18-5-22 下午12:33
 * 模块名称：admin
 */

/*
 * 作者：xuda
 * 创建时间：18-5-14 上午8:47
 * 模块名称：admin
 */

package com.fyerp.admin.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.domain.Project;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ProjectCategoryVO {

    /**
     * 类目id
     */
    @Id
    @JsonProperty("id")
    private Integer categoryId;

    /**
     * 类目名称
     */
    @JsonProperty("name")
    private String categoryName;

    @Transient
    @ApiModelProperty(allowableValues = "projectCategory")
    @JsonProperty(defaultValue = "projectCategory",value = "projectCategory")
    private String type = this.getClass().getName();

    @ManyToMany(targetEntity = Project.class,cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "ProjectCategoryProject", joinColumns = {@JoinColumn(name = "categoryId")}, inverseJoinColumns = {@JoinColumn(name = "projectId")})
    private Set<Project> projects = new HashSet<>();

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("creationDate")
    private Date createTime;
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("updatedDate")
    private Date updateTime;

}
