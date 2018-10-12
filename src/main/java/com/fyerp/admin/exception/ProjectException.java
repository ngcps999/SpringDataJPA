/*
 * 作者：xuda
 * 创建时间：18-6-1 下午4:50
 * 模块名称：admin
 */

package com.fyerp.admin.exception;

import com.fyerp.admin.enums.ResultEnum;
import lombok.Getter;

@Getter
public class ProjectException extends RuntimeException {
    private Integer code;

    public ProjectException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());

        this.code = resultEnum.getCode();
    }

    public ProjectException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
