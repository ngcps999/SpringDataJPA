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
import com.fyerp.admin.utils.MyComment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    @JsonProperty(value = "id")
    private Integer contractId;

    @MyComment("合同名称")
    @JsonProperty("name")
    private String contractName;


    @Transient
    @JsonProperty(defaultValue = "contract")
    @ApiModelProperty(allowableValues = "contract")
    private String type = "contract";

    @MyComment("合同类型")
    private String contractType;

    @MyComment("合同编号")
    @JsonProperty("code")
    private String contractCode;

    @MyComment("合同状态")
    @JsonProperty("state")
    private String contractState;

    @MyComment("合同费用")
    @JsonProperty("amount")
    private Double contractMoney;

    @MyComment("税率")
    @JsonProperty("taxRate")
    private Double contractTaxRate;

    @MyComment("税费")
    @JsonProperty("tax")
    private Double contractTax;

    @MyComment("总费用")
    @JsonProperty("totalAmount")
    private Double totalMoney;

    @MyComment("是否更改")
    @JsonProperty("isChanged")
    private Boolean isChange;

    @MyComment("是否销毁")
    @JsonProperty("isObsoleted")
    private Boolean isDestroy;

    @MyComment("生效时间")
    @JsonProperty("effectiveDate")
    private Date effectTime;

    @MyComment("失效时间")
    @JsonProperty("obsoletedDate")
    private Date lostEffectTime;

    @MyComment("截止时间")
    @JsonProperty("endDate")
    private Date endTime;

    @MyComment("审核时间")
    @JsonProperty("approvalDate")
    private Date auditTime;

    @MyComment("备注")
    private String note;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("creationDate")
    @MyComment("创建时间")
    private Date createTime;

    @JsonProperty("updatedDate")
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @MyComment("更新时间")
    private Date updateTime;

    @OneToMany(targetEntity = FileInfo.class,cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<FileInfo> files = new HashSet<>();

}
