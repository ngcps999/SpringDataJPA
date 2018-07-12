package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.utils.MyComment;
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
    @MyComment("客户名称")
    private String  customerName;

    @Transient
    @JsonProperty(defaultValue = "customer")
    @ApiModelProperty(allowableValues = "customer")
    private String type = "customer";

    @MyComment("客户类型")
    private String customerType;

    @MyComment("客户编号")
    private String code;

    @MyComment("联系地址")
    private String address;

    @MyComment("联系方式")
    private String contact;

    @MyComment("联系人")
    private String linkman;

    /**
     * 创建时间
     */
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("creationDate")
    @MyComment("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("updatedDate")
    @MyComment("更新时间")
    private Date updateTime;

}
