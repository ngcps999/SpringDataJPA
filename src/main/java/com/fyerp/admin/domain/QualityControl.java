package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.utils.MyComment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @MyComment("备注")
    private String remark;

    @Transient
    @JsonProperty(defaultValue = "qualityControl")
    @ApiModelProperty(allowableValues = "qualityControl")
    private String type = "qualityControl";

//    @MyComment("文件名")
//    private String fileName;
//
//    @MyComment("文件路径")
//    private String filePath;

    @OneToMany(targetEntity = FileInfo.class,cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<FileInfo> files = new HashSet<>();


}
