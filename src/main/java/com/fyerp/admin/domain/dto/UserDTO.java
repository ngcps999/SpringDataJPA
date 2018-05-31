/*
 * 作者：xuda
 * 创建时间：18-5-11 上午10:00
 * 模块名称：admin
 */

package com.fyerp.admin.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.domain.Department;
import com.fyerp.admin.domain.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class UserDTO {

    /**
     * 用户Id
     */
    @Id
    @JsonProperty("id")
    private Long userId;

    @Transient
    @JsonProperty(value = "type", index = 0, defaultValue = "User")
    @ApiModelProperty(allowableValues = "User", value = "User", dataType = "String", required = true, name = "User")
    private String type;

    /**
     * 性别
     */
//    @NotBlank
    private String gender;

    /**
     * 密码
     */
//    @NotBlank
    private String password;

    /**
     * 用户状态,0:用户未输入验证码, 1:正常状态,2：用户被锁定.
     */
    @JsonProperty("status")
    private Integer state;

//    /**
//     * 一个用户具有多个角色
//     */
////    @NotEmpty
//    @JsonIgnore
////    @JsonProperty("children")
//    @ManyToMany(cascade = {CascadeType.ALL})//立即从数据库中加载数据；
//    @JoinTable(name = "UserRole", joinColumns = {@JoinColumn(name = "userId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
//    private List<Role> roles;

    @JsonIgnore
    @CreatedDate
    private Date createTime;

    @JsonIgnore
    @LastModifiedDate
    private Date updateTime;

}
