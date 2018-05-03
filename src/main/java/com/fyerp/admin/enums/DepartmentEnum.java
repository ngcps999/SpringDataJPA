/*
 * 作者：xuda
 * 创建时间：18-5-2 下午2:29
 * 模块名称：admin
 */

package com.fyerp.admin.enums;

public enum DepartmentEnum {
    YFB(0,"研发部"),
    JSB(1,"技术部"),
    SCB(2,"市场部"),
    GCB(3,"工程部")
    ;

    private Integer code;
    private String name;

    DepartmentEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
