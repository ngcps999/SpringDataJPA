/*
 * 作者：xuda
 * 创建时间：18-6-6 下午4:05
 * 模块名称：admin
 */

/*
 * 作者：xuda
 * 创建时间：18-6-6 下午4:02
 * 模块名称：admin
 */

package com.fyerp.admin.domain.base;

import java.io.Serializable;

public interface BaseModel<ID extends Serializable> extends Serializable {

    ID getId();
}


