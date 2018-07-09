package com.fyerp.admin.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 供应商-实体类
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
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
}
