package com.fyerp.admin.exception;

import com.fyerp.admin.enums.ResultEnum;
import lombok.Data;

/**
 * @author:xiasc
 * @Date:2018/7/11
 * @Time:16:27
 **/
@Data
public class OtherException extends RuntimeException{

    private Integer code;

    public OtherException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public OtherException(Integer code, String message) {
        super(message);
        this.code = code;

    }
}
