/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：Result.java
 * 作者：xuda
 * 时间：18-4-9 上午11:50
 *
 */

package com.fyerp.admin.domain;

import lombok.Data;

@Data
public class Result<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;


    public Result(String msg) {
        this.msg = msg;
    }

    public Result() {
    }
}
