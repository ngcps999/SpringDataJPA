/*
 * 作者：xuda
 * 创建时间：18-6-7 上午11:24
 * 模块名称：admin
 */

/*
 * 作者：xuda
 * 创建时间：18-6-1 下午4:50
 * 模块名称：admin
 */

package com.fyerp.admin.exception;

import com.fyerp.admin.enums.ResultEnum;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException {
    private Integer code;

    public UserException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());

        this.code = resultEnum.getCode();
    }

    public UserException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
