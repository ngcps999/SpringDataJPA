package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.utils.MyComment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
