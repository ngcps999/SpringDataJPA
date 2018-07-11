package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.javafx.beans.IDProperty;
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
 * 客户-实体类
 */

@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Data
public class Customer implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty(value = "id")
    private Integer customerId;

    @JsonProperty(value = "name")
    private String  customerName;

    @Transient
    @JsonProperty(defaultValue = "customer")
    @ApiModelProperty(allowableValues = "customer")
    private String type = "customer";

    private String customerType;

    private String code;

    private String address;

    private String contact;

    private String linkman;

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
    @Column(columnDefinition = " COMMENT '主键，自动生成'")
    private Date updateTime;

}
