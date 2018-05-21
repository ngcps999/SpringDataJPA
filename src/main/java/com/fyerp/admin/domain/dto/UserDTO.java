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
import com.fyerp.admin.domain.Task;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserDTO {

    /**
     * 用户Id
     */
    @Id
    @JsonProperty("id")
    private Long userId;

    /**
     * 一个用户具有多个角色
     */
//    @NotEmpty
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)//立即从数据库中加载数据；
    @JoinTable(name = "UserRole", joinColumns = {@JoinColumn(name = "userId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @CreatedDate
    private Date createTime;

    @JsonIgnore
    @LastModifiedDate
    private Date updateTime;

}
