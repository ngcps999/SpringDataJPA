/*
 * 作者：xuda
 * 创建时间：18-6-7 下午1:09
 * 模块名称：admin
 */

/*
 * 作者：xuda
 * 创建时间：18-5-8 上午11:01
 * 模块名称：admin
 */

package com.fyerp.admin.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.domain.Task;
import com.fyerp.admin.domain.User;
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
import java.util.List;
import java.util.Set;

@EntityListeners(AuditingEntityListener.class)
@Data
public class DepartmentVO {

    @JsonProperty(value = "id")
    @Id
    private Long departmentId;

    /**
     * 部门名称
     */
    @JsonProperty("name")
    private String depName;

    /**
     * 部门描述
     */
    @JsonProperty("description")
    private String depDesc;

    @Transient
    @JsonProperty(defaultValue = "department")
    @ApiModelProperty(allowableValues = "department")
    private String type = "department";

    @JsonProperty(value = "children")
    @ManyToMany(targetEntity = Task.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "DepartmentTask", joinColumns = {@JoinColumn(name = "departmentId")}, inverseJoinColumns = {@JoinColumn(name = "taskId")})
    private Set<Task> tasks = new HashSet<>();

//    @OneToOne
//    @JoinColumn(name = "departmentId")
//    private Plan plan;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("creationDate")
    private Date createTime;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("updatedDate")
    private Date updateTime;

    public DepartmentVO() {
    }
}
