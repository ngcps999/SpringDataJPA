/*
 * 作者：xuda
 * 创建时间：18-5-8 上午11:01
 * 模块名称：admin
 */

package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Table(name = "department")
@Data
public class Department {

    @JsonProperty("id")
    @Id
    @GeneratedValue(generator = "generator",strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "generator",strategy = "native")
    private Long departmentId;
    @JsonProperty("name")
    private String depName;

    @Transient
    @JsonProperty(value = "type",index = 0, defaultValue = "Department")
    @ApiModelProperty(allowableValues = "Department",value = "Department",dataType = "String",required = true,name = "Department")
    private String type;

    @JsonIgnore
    @JsonProperty("children")
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH})//立即从数据库中加载数据；
    @JoinTable(name = "DepartmentUser", joinColumns = {@JoinColumn(name = "departmentId")}, inverseJoinColumns = {@JoinColumn(name = "userId")})
    private List<User> users;

//    @JsonIgnore
//    @ManyToMany
//    @JoinTable(name = "TaskDepartment",joinColumns = {@JoinColumn(name = "departmentId")},inverseJoinColumns = {@JoinColumn(name = "taskId")})
//    private List<Task> tasks;

//    @OneToOne
//    @JoinColumn(name = "departmentId")
//    private Plan plan;

    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @JsonProperty("creationDate")
    private Date createTime;

    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @JsonProperty("updatedDate")
    private Date updateTime;

   
}
