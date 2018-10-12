/*
 * 作者：xuda
 * 创建时间：18-5-31 上午10:13
 * 模块名称：admin
 */

package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.utils.MyComment;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Data
public class FileInfo {

    @JsonProperty(value = "id")
    @Id
    @GeneratedValue
    private Long fileId;

    @MyComment("文件路径")
    private String path;

    @MyComment("文件名")
    private String name;

    public FileInfo(String path,String name){
        this.path = path;
        this.name = name;
    }

    public FileInfo(String path) {
        this.path = path;
    }


    public FileInfo() {
    }

}
