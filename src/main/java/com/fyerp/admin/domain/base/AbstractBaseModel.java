/*
 * 作者：xuda
 * 创建时间：18-6-6 下午4:06
 * 模块名称：admin
 */

package com.fyerp.admin.domain.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
@Data
public abstract class AbstractBaseModel<ID extends Serializable> implements BaseModel<ID> {

    @ApiModelProperty(value="创建者ID")
    @Column(columnDefinition="int default 0")
    private int creator = 0;

    @ApiModelProperty(value="创建时间")
    @Column(name="create_time")
    private Timestamp createTime;

    @ApiModelProperty(value="最后修改人")
    @Column(columnDefinition="int default 0")
    private int updator = 0;

    @ApiModelProperty(value="最后修改时间")
    @Column(name="update_time")
    private Timestamp updateTime;

}
