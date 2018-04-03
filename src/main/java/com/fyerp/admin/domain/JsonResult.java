/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：JsonResult.java
 * 作者：xuda
 * 时间：18-4-3 下午3:30
 *
 */

package com.fyerp.admin.domain;

/**
 * Json结果集
 */
public class JsonResult {

    private String status = null;
    private Object result = null;

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
