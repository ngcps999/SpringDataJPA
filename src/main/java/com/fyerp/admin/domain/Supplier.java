package com.fyerp.admin.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 供应商-实体类
 */
@Data
@MappedSuperclass
public class Supplier implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Integer supplierId;

    @JsonProperty("name")
    private String supplierName;

    @Transient
    @JsonProperty(defaultValue = "supplier")
    @ApiModelProperty(allowableValues = "supplier")
    private String type;

    private String companyName;

    private String linkman;

    private String contact;

    private String address;

    private String remark;

    /**
     * 创建时间
     */
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("creationDate")
    private Date createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("updatedDate")
    private Date updateTime;
}
