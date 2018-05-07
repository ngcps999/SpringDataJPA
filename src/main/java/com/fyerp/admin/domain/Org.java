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
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: xuda
 * @Date: 2018/4/9
 * @Time: 下午3:57
 */
@Entity
@DynamicUpdate
//@Data
public class Org {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 部门名称
     */
    private String depName;
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

    public Org(String depName, Integer parentId, String path, Integer sort) {
        this.depName = depName;
        this.parentId = parentId;
        this.path = path;
        this.sort = sort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Org{" +
                "id=" + id +
                ", depName='" + depName + '\'' +
                ", parentId=" + parentId +
                ", path='" + path + '\'' +
                ", sort=" + sort +
                '}';
    }
}
