/*
 * 作者：xuda
 * 创建时间：18-5-21 下午4:31
 * 模块名称：admin
 */

package com.fyerp.admin.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.respository.UserRespository;
import com.fyerp.admin.service.UserService;
import com.fyerp.admin.utils.convert.User2UserDTOConverter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Data
public class DepartmentDTO implements Serializable {

    @JsonProperty(value = "id")
    @Id
    @GeneratedValue
    private Long departmentId;

    /**
     * 部门名称
     */
    @JsonProperty("name")
    private String depName;

    /**
     * 部门描述
     */
    @JsonProperty("description")
    private String depDesc;

    @Transient
    @JsonProperty(defaultValue = "department")
    @ApiModelProperty(allowableValues = "department")
    private String type = "department";

    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @JsonProperty("creationDate")
    private Date createTime;

    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @JsonProperty("updatedDate")
    private Date updateTime;

    public DepartmentDTO() {
    }
}
