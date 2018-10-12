package com.fyerp.admin.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.utils.MyComment;
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
    @MyComment("供应商名称")
    private String supplierName;

    @Transient
    @JsonProperty(defaultValue = "supplier")
    @ApiModelProperty(allowableValues = "supplier")
    private String type;

    // 1-相机供应商  2-飞机租赁供应商  3-其他
    @Transient
    private Integer supplierType;

    @MyComment("公司名")
    private String companyName;

    @MyComment("联系人")
    private String linkman;

    @MyComment("联系方式")
    private String contact;

    @MyComment("地址")
    private String address;

    @MyComment("备注")
    private String remark;

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
