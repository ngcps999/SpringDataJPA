/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：Contract.java
 * 作者：xuda
 * 时间：18-4-10 上午9:42
 *
 */

package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * 合同
 *
 * @Author: xuda
 * @Date: 2018/4/10
 * @Time: 上午9:42
 */
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Data
public class Contract {

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Integer contractId;
    @JsonProperty("name")
    private String contractName;
    @JsonProperty("type")
    private String contractType;
    @JsonProperty("code")
    private String contractCode;
    @JsonProperty("state")
    private String contractState;
    @JsonProperty("amount")
    private Double contractMoney;
    @JsonProperty("tax_rate")
    private Double contractTaxRate;
    @JsonProperty("tax")
    private Double contractTax;
    @JsonProperty("totalAmount")
    private String totalMoney;

    @JsonProperty("isChanged")
    private Boolean isChange;
    @JsonProperty("isObsoleted")
    private Boolean isDestroy;
    @JsonProperty("effectiveDate")
    private Date effectTime;
    @JsonProperty("obsoletedDate")
    private Date lostEffectTime;
    @JsonProperty("endDate")
    private Date endTime;
    @JsonProperty("approvalDate")
    private Date auditTime;
    private String note;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("creationDate")
    private Date createTime;

    @JsonProperty("updatedDate")
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;

}
