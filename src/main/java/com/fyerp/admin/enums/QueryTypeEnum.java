/*
 * 作者：xuda
 * 创建时间：18-6-7 上午10:18
 * 模块名称：admin
 */

package com.fyerp.admin.enums;

import io.swagger.annotations.ApiModel;

@ApiModel(value="查询条件支持的过滤方式")
public enum QueryTypeEnum {
    like,
    equal,
    ne,
    lt,
    lte,
    gt,
    gte
}
