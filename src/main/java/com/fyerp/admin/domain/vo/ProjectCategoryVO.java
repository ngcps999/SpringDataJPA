/*
 * 作者：xuda
 * 创建时间：18-5-22 下午12:33
 * 模块名称：admin
 */

/*
 * 作者：xuda
 * 创建时间：18-5-14 上午8:47
 * 模块名称：admin
 */

package com.fyerp.admin.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.domain.Project;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class ProjectCategoryVO {

    /**
     * 类目id
     */
    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Integer categoryId;

    /**
     * 类目名称
     */
    @JsonProperty("name")
    private String categoryName;

    @Transient
    @ApiModelProperty(name = "type",value = "ProjectCategory",allowableValues = "ProjectCategory")
    @JsonProperty(index = 0,defaultValue = "ProjectCategory",value = "ProjectCategory")
    private String type = this.getClass().getName();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    private List<ProjectVO> children;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("creationDate")
    private Date createTime;
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("updatedDate")
    private Date updateTime;

}
