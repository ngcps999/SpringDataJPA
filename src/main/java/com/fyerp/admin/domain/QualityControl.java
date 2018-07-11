package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author:xiasc
 * @Date:2018/7/10
 * @Time:16:28
 * 质检报告
 **/
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
@Data
public class QualityControl implements Serializable {
    @Id
    @GeneratedValue
    @JsonProperty(value = "id")
    private Integer qualityControlId;

    private String remark;

    @Transient
    @JsonProperty(defaultValue = "qualityControl")
    @ApiModelProperty(allowableValues = "qualityControl")
    private String type = "qualityControl";


    private String fileName;

    private String address;
}
