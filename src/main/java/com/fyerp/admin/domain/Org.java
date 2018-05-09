/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：Org.java
 * 作者：xuda
 * 时间：18-4-9 下午3:57
 *
 */

package com.fyerp.admin.domain;

//import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Author: xuda
 * @Date: 2018/4/9
 * @Time: 下午3:57
 */
@Entity
@DynamicUpdate
@Data
@EntityListeners(AuditingEntityListener.class)
public class Org {

    @Id
    @GeneratedValue
    private Integer orgId;

    /**
     * 部门名称
     */
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "orgId")
    private List<Department> departments;

    /**
     * 父级id号
     */
    private Integer parentId;
    /**
     * id路径
     */
    private String path;
    /**
     * 排序
     */
    private Integer sort;

    @JsonIgnore
    @CreatedDate
    private Date createTime;
    @JsonIgnore
    @LastModifiedDate
    private Date updateTime;
    /**
     * 无参构造
     */
    public Org() {
    }

    public Org(List<Department> departments, Integer parentId, String path, Integer sort, Date createTime, Date updateTime) {
        this.departments = departments;
        this.parentId = parentId;
        this.path = path;
        this.sort = sort;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Org{" +
                "orgId=" + orgId +
                ", parentId=" + parentId +
                ", path='" + path + '\'' +
                ", sort=" + sort +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
