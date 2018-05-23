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
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@Data
public class ProjectCategory {

    /**
     * 类目id
     */
    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Integer categoryId;

    /**
     * 类目名称
     */
    @JsonProperty("name")
    private String categoryName;

    @Transient
    @JsonProperty(value = "type",index = 0, defaultValue = "ProjectCategory")
    @ApiModelProperty(allowableValues = "ProjectCategory",value = "ProjectCategory",dataType = "String",required = true,name = "ProjectCategory")
    private String type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")

    @JsonProperty("children")
    private List<Project> projects;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("creationDate")
    private Date createTime;
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("updatedDate")
    private Date updateTime;

}
