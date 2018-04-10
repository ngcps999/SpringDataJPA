/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：ProjectStatusEnum.java
 * 作者：xuda
 * 时间：18-4-4 下午2:45
 *
 */

package com.fyerp.admin.enums;

import lombok.Getter;

/**
 * @Author: xuda
 * @Date: 2018/4/4
 * @Time: 下午2:45
 */
@Getter
public enum ProjectStatusEnum {

    DOING(0,"正在进行"),
    WARNING(1,"遇到问题"),
    FINISH(2,"完成")
    ;

    private Integer code;
    private String message;

    ProjectStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
