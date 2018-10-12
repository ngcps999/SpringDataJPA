/*
 * 作者：xuda
 * 创建时间：18-7-5 上午9:28
 * 模块名称：admin
 */

package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Entity
@Data
public class FileEntity {

    @Id
    @JsonProperty("id")
    private String fileId;

    @JsonProperty("name")
    private String fileName;
    @JsonProperty("type")
    private String fileType;

    @JsonProperty("data")
    private byte[] fileData;

    @JsonProperty("ext")
    private String fileExt;

    /**
     * 将byte数组转为stream
     *
     * @return
     */
    @Transient
    public InputStream getInputStream() {
        if (fileData != null) {
            return new ByteArrayInputStream(fileData);
        }
        return null;
    }

}
