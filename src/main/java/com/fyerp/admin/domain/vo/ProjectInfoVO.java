/*
 * 作者：xuda
 * 创建时间：18-5-14 下午1:49
 * 模块名称：admin
 */

package com.fyerp.admin.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class ProjectInfoVO {

    /**
     * 项目id
     */
    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Integer projectId;

    /**
     * 项目名称
     */
    @JsonProperty("name")
    private String projectName;

    /**
     * 项目计划开始时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date planStartDate;

    /**
     * 项目计划完成时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date planEndDate;

    /**
     * 项目实际开始时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date realStartDate;

    /**
     * 项目实际完成时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date realEndDate;
}
