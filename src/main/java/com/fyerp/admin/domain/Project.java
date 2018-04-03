/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：Project.java
 * 作者：xuda
 * 时间：18-4-3 下午3:51
 *
 */

package com.fyerp.admin.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: xuda
 * @Date: 2018/4/3
 * @Time: 下午3:51
 * 项目实体
 */
@Entity
public class Project {

    /**
     * 项目id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 项目名称
     */
    private String project_name;

    /**
     * 项目开始时间
     */
    private Date startdate;

    /**
     * 项目结束时间
     */
    private Date enddate;

    /**
     * 项目成员
     */
    private String member;

    /**
     * 项目状态：0未进行，1正在进行，2遇到问题，3遇到问题
     */
    private Character project_state;

    /**
     * 项目描述
     */
    private String project_desc;

    /**
     * 创建时间
     */
    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public Character getProject_state() {
        return project_state;
    }

    public void setProject_state(Character project_state) {
        this.project_state = project_state;
    }

    public String getProject_desc() {
        return project_desc;
    }

    public void setProject_desc(String project_desc) {
        this.project_desc = project_desc;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", project_name='" + project_name + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", member='" + member + '\'' +
                ", project_state=" + project_state +
                ", project_desc='" + project_desc + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
