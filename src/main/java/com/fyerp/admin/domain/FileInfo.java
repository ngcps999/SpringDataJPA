/*
 * 作者：xuda
 * 创建时间：18-5-31 上午10:13
 * 模块名称：admin
 */

package com.fyerp.admin.domain;

public class FileInfo {
    private String path;

    public FileInfo(String path) {
        this.path = path;
    }


    public FileInfo() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
