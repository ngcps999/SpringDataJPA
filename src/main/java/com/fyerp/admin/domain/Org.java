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
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
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
    private Integer order;

    /**
     * 无参构造
     */
    public Org() {
    }

    public Org(String depName, Integer parentId, String path, Integer order) {
        this.depName = depName;
        this.parentId = parentId;
        this.path = path;
        this.order = order;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Org{" +
                "id=" + id +
                ", depName='" + depName + '\'' +
                ", parentId=" + parentId +
                ", path='" + path + '\'' +
                ", order=" + order +
                '}';
    }
}
