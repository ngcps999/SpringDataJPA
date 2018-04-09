/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：ResultEnum.java
 * 作者：xuda
 * 时间：18-4-9 上午10:03
 *
 */

package com.fyerp.admin.enums;

public enum ResultEnum {
    UNKNOW_ERROR("-1","未知错误"),
    SUCCESS("0","成功"),
    ;

    private String status;
    private Object result;

    ResultEnum(String status, Object result) {
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
