/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：Permission.java
 * 作者：xuda
 * 时间：18-4-11 下午2:33
 *
 */

package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.utils.MyComment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: xuda
 * @Date: 2018/4/11
 * @Time: 下午2:33
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class Permission implements Serializable {
    private static final long serialVersionUID = 8787425039423616077L;
    /**
     * 主键Id
     */
    @Id
    @GeneratedValue
    @JsonProperty(value = "id")
    private Long permissionId;

    /**
     * 权限名称
     */
    @MyComment("权限名称")
    private String name;


    @Transient
    @JsonProperty(defaultValue = "permission",index = 1)
    @ApiModelProperty(allowableValues = "permission")
    private String type = "permission";

    /**
     * 授权链接
     */
    @MyComment("授权链接")
    private String url;

    /**
     * 权限描述
     */
    @MyComment("权限描述")
    private String permission;

    /**
     * 父节点Id
     */
    @MyComment("父节点ID")
    private Integer parentId;


    private Integer strategy;

    /**
     * 角色-权限多对多关系
     */
//    @JsonIgnore
////            @JsonProperty("children")
//    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
//    @JoinTable(name = "RolePermission",joinColumns={@JoinColumn(name = "permissionId")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
//    private List<Role> roles;

    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @JsonProperty("creationDate")
    @MyComment("创建时间")
    private Date createTime;


    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @JsonProperty("updatedDate")
    @MyComment("修改时间")
    private Date updateTime;


}
