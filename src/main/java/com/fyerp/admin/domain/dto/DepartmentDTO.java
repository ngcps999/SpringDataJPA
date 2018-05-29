/*
 * 作者：xuda
 * 创建时间：18-5-21 下午4:31
 * 模块名称：admin
 */

package com.fyerp.admin.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.respository.UserRespository;
import com.fyerp.admin.service.UserService;
import com.fyerp.admin.utils.convert.User2UserDTOConverter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
public class DepartmentDTO {

    @Id
    @JsonProperty("id")
    private Long departmentId;

    private String depName;

    @Transient
    @JsonProperty(value = "type",defaultValue = "department")
    @ApiModelProperty(allowableValues = "department")
    private String type;

    @JsonProperty("children")
    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)//立即从数据库中加载数据；
    @JoinTable(name = "DepartmentUser", joinColumns = {@JoinColumn(name = "departmentId")}, inverseJoinColumns = {@JoinColumn(name = "userId")})
    private List<User> users;

}
