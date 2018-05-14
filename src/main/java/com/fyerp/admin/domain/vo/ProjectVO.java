/*
 * 作者：xuda
 * 创建时间：18-5-14 下午12:49
 * 模块名称：admin
 */

package com.fyerp.admin.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
public class ProjectVO {

    @JsonProperty("name")
    private String categoryName;

    /**
     * 项目类型编号
     */
    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("projects")
    private List<ProjectInfoVO> projectInfoVOList;


}
