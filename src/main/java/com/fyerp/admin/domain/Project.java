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

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

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
@DynamicUpdate
@Data
public class Project {

    /**
     * 无参构造
     */
    public Project() {
    }

    /**
     * 有参构造
     * @param project_name
     * @param startdate
     * @param enddate
     * @param member
     * @param project_state
     * @param project_desc
     */
    public Project(String project_name, Date startdate, Date enddate, String member, Integer project_state, String project_desc) {
        this.project_name = project_name;
        this.startdate = startdate;
        this.enddate = enddate;
        this.member = member;
        this.project_state = project_state;
        this.project_desc = project_desc;
    }

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
     * 项目状态：0未进行，1正在进行，2遇到问题
     */
    private Integer project_state;

    /**
     * 项目描述
     */
    private String project_desc;

}
