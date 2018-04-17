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

import com.fyerp.admin.enums.ProjectStatusEnum;
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
     * @param projectName
     * @param startdate
     * @param enddate
     * @param member
     * @param projectState
     * @param projectDesc
     */
    public Project(String projectName, Date startdate, Date enddate, String member, Integer projectState, String projectDesc) {
        this.projectName = projectName;
        this.startdate = startdate;
        this.enddate = enddate;
        this.member = member;
        this.projectState = projectState;
        this.projectDesc = projectDesc;
    }

    /**
     * 项目id
     */
    @Id
    @GeneratedValue
    private Integer projectId;

    /**
     * 项目名称
     */
    private String projectName;

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
    private Integer projectState = ProjectStatusEnum.DOING.getCode();

    /**
     * 项目描述
     */
    private String projectDesc;

}
