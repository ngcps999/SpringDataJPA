package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.javafx.beans.IDProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

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
}
