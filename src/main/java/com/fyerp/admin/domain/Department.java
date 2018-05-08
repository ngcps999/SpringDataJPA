/*
 * 作者：xuda
 * 创建时间：18-5-8 上午11:01
 * 模块名称：admin
 */

package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicUpdate
@Data
public class Department {

    @JsonProperty("id")
    @Id
    @GeneratedValue
    private Long departmentId;
    @JsonProperty("name")
    private String depName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "departmentId")
    private List<User> users;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "ProjectDepartment",joinColumns = {@JoinColumn(name = "departmentId")},inverseJoinColumns = {@JoinColumn(name = "projectId")})
    private List<Project> projects;

}
