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

    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数输入不正确"),
    DATE_FORMAT_ERROR(2, "日期格式不正确,正确格式是2018-01-01"),
    PROJECT_NOT_EXIST(10, "项目不存在"),
    PROJECT_OWNER_ERROR(11, "该项目不属于当前用户"),
    PROJECT_STATUS_ERROR(12, "项目状态不正确,0正在进行,1遇到问题,2完成"),
    LOGIN_SUCCESS(20, "登陆成功"),
    LOGIN_USERNAME_FAIL(21, "登录失败,用户名不存在"),
    LOGIN_PASSWORD_FAIL(22, "登陆失败,密码错误"),
    TASK_CANCEL_SUCCESS(23, "任务取消成功"),
    TASK_FINISH_SUCCESS(24, "任务完成成功"),
    LOGOUT_SUCCESS(24, "登出成功");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
