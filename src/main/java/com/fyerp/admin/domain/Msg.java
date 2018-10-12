/*
 * 作者：xuda
 * 创建时间：18-4-20 上午9:59
 * 模块名称：admin
 */

package com.fyerp.admin.domain;

public class Msg {

    private String title;
    private String content;
    private String extraInfo;

    public Msg(String title, String content, String etraInfo) {
        super();
        this.title = title;
        this.content = content;
        this.extraInfo = etraInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
