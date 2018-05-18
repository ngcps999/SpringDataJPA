/*
 * 作者：xuda
 * 创建时间：18-5-8 上午11:01
 * 模块名称：admin
 */

package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicUpdate;
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
public class Department {

    @JsonProperty("id")
    @Id
    @GeneratedValue
    private Long departmentId;
    @JsonProperty("name")
    private String depName;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)//立即从数据库中加载数据；
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

//    public List<Task> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(List<Task> tasks) {
//        this.tasks = tasks;
//    }

//    public Plan getPlan() {
//        return plan;
//    }
//
//    public void setPlan(Plan plan) {
//        this.plan = plan;
//    }

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

}
