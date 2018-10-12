/*
 * 作者：xuda
 * 创建时间：18-6-7 上午9:55
 * 模块名称：admin
 */

package com.fyerp.admin.domain.base;

import com.fyerp.admin.utils.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class MarkDeleteableModel <ID extends Serializable> extends AbstractBaseModel<ID>{
    private static final long serialVersionUID = -1880548221110317053L;

    @ApiModelProperty(value="标记删除字段 0未删除 1已删除 ")
    @Column(name = "del", columnDefinition = "tinyint default 0")
    private int del = Constants.DEL_NO;

}
