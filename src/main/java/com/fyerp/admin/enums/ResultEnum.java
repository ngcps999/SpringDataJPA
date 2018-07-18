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
    LOGOUT_SUCCESS(24, "登出成功"),
    STRATEGY_ERROR(3,"Strategy输入错误，0新增1更新2删除"),
    USER_STRATEGY_DELETE(25,"用户已停用"),
    LOGIN_FAILED(26,"登录失败，请检查用户名或密码"),
    EMPTY_LOGIN_PARAM(27,"用户名和密码不能为空"),
    LOGIN_AGAIN(28,"请重新登录"),
    DELETE_FAILED(29,"删除失败"),
    DELETE_SUCCESS(30,"删除成功"),
    RESULT_FAILED(-1,"系统异常"),
    NEED_LOGIN(31,"请登录"),
    UPLOAD_FAILED(32,"文件上传失败"),
    DOWNLOAD_FAILED(33,"文件下载失败");

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
