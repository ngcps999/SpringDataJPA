/*
 * 作者：xuda
 * 创建时间：18-6-7 上午9:36
 * 模块名称：admin
 */

package com.fyerp.admin.exception;

import com.fyerp.admin.enums.ResultEnum;
import lombok.Getter;

@Getter
public class DepartmentException extends RuntimeException {
    private Integer code;

    public DepartmentException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public DepartmentException(Integer code, String message) {
        super(message);
        this.code = code;

    }
}
