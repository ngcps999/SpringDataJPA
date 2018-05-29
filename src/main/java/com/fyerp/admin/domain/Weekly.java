/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：Weekly.java
 * 作者：xuda
 * 时间：18-4-10 上午9:42
 *
 */

package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @Author: xuda
 * @Date: 2018/4/10
 * @Time: 上午9:42
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Data
@EntityListeners(AuditingEntityListener.class)
public class Weekly {

    @Id
    @GeneratedValue
    private int id;
    private Date startDate;
    private Date endDate;
    private String content;
    private String completeness;
    private String postil;

    @CreatedDate
    @JsonProperty("creationDate")
    private Date createTime;

    @LastModifiedDate
    @JsonProperty("updatedDate")
    private Date updateTime;

}
