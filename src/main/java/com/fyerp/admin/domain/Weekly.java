/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：Weekly.java
 * 作者：xuda
 * 时间：18-4-10 上午9:42
 *
 */

package com.fyerp.admin.domain;

//import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Author: xuda
 * @Date: 2018/4/10
 * @Time: 上午9:42
 */
@Entity
@DynamicUpdate
//@Data
public class Weekly {
    @Id
    @GeneratedValue
    private int id;
    private Timestamp startdate;
    private Timestamp enddate;
    private String content;
    private String completeness;
    private String postil;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    public Timestamp getEnddate() {
        return enddate;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCompleteness() {
        return completeness;
    }

    public void setCompleteness(String completeness) {
        this.completeness = completeness;
    }

    public String getPostil() {
        return postil;
    }

    public void setPostil(String postil) {
        this.postil = postil;
    }

    @Override
    public String toString() {
        return "Weekly{" +
                "id=" + id +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", content='" + content + '\'' +
                ", completeness='" + completeness + '\'' +
                ", postil='" + postil + '\'' +
                '}';
    }
}
