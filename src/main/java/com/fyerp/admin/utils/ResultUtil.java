/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：ResultUtil.java
 * 作者：xuda
 * 时间：18-4-9 上午11:51
 *
 */

package com.fyerp.admin.utils;


import com.fyerp.admin.domain.Result;

public class ResultUtil {

    private final static Result result = new Result();

    public static Result success(Object object) {

        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code,String msg) {

        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
